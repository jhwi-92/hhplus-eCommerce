package hhplus.ecommoerce.biz.application.facade;

import hhplus.ecommoerce.biz.application.domain.entity.Order;
import hhplus.ecommoerce.biz.application.domain.service.DataPlatformService;
import hhplus.ecommoerce.biz.application.domain.service.OrderService;
import hhplus.ecommoerce.biz.application.domain.service.PaymentService;
import hhplus.ecommoerce.biz.application.domain.service.ProductService;
import hhplus.ecommoerce.biz.application.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
@Component
@Slf4j
public class OrderFacade {

    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final DataPlatformService dataPlatformService;
    private final TransactionTemplate transactionTemplate;

    //주문결제
    //현재 분리하기 좀 애매해서 락에 대해서 공부하고 추후에 @EventListener사용하여 분리할 수정예정입니다
    @Transactional
    public Order orderPayment(Order order) {
        int maxRetries = 20;
        int retryCount = 0;
        boolean success = false;
        Order newOrder = null;

        while (!success && retryCount < maxRetries) {
            try {
                newOrder = transactionTemplate.execute(status -> {
                    // 새로운 트랜잭션에서 서비스 실행
                    return executeOrderPayment(order);
                });
                success = true;
            } catch (OptimisticLockingFailureException e) {
                retryCount++;
                log.warn("낙관적 락 실패. 재시도 횟수: {}", retryCount);
                if (retryCount >= maxRetries) {
                    log.error("최대 재시도 횟수 도달. 주문 처리 실패.");
                    throw new RuntimeException("주문 처리 " + maxRetries + "회 재시도 실패", e);
                }
                try {
                    Thread.sleep((long) Math.pow(2, retryCount) * 100); // 지수 백오프
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("재시도 대기 중 쓰레드 중단", ie);
                }
            }
        }

        if (!success) {
            throw new RuntimeException("주문 처리 실패");
        }

        return newOrder;
    }

    @Transactional
    public Order orderPaymentPessimistic(Order order) {
        int totalPrice = order.getPrice() * order.getQuantity();

        // 재고확인 후 차감 (비관적 락 사용)
        productService.decreaseProductWithPessimisticLock(order.getProductId(), order.getQuantity());

        // 유저확인 후 포인트 차감 (비관적 락 사용)
        userService.decreaseUserPointWithPessimisticLock(order.getUserId(), totalPrice);

        // 주문 생성
        Order newOrder = orderService.createOrder(order.getUserId(), order.getProductId(), order.getQuantity(), order.getPrice());

        // 결제 요청
        paymentService.sendPayment();

        // 외부 데이터 수집 플랫폼으로 데이터 전송
        dataPlatformService.sendDataPlatform();

        return newOrder;
    }

    private Order executeOrderPayment(Order order) {
        int totalPrice = order.getPrice() * order.getQuantity();

        // 재고확인 후 차감
        productService.decreaseProduct(order.getProductId(), order.getQuantity());

        // 유저확인 후 포인트 차감
        userService.decreaseUserPoint(order.getUserId(), totalPrice);

        // 주문 생성
        Order newOrder = orderService.createOrder(order.getUserId(), order.getProductId(), order.getQuantity(), order.getPrice());

        // 결제 요청
        paymentService.sendPayment();

        // 외부 데이터 수집 플랫폼으로 데이터 전송
        dataPlatformService.sendDataPlatform();

        return newOrder;
    }
}
