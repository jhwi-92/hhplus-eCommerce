package hhplus.ecommoerce.biz.application.facade;

import hhplus.ecommoerce.biz.application.domain.entity.Order;
import hhplus.ecommoerce.biz.application.domain.entity.Payment;
import hhplus.ecommoerce.biz.application.domain.service.DataPlatformService;
import hhplus.ecommoerce.biz.application.domain.service.OrderService;
import hhplus.ecommoerce.biz.application.domain.service.PaymentService;
import hhplus.ecommoerce.biz.application.domain.service.ProductService;
import hhplus.ecommoerce.biz.application.domain.service.UserService;
import hhplus.ecommoerce.infra.DataPlatformClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
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
    private final ApplicationEventPublisher eventPublisher;
    private final TransactionTemplate transactionTemplate;

    //주문결제
    //@EventListener사용하여 분리할 수정예정입니다
    @Transactional
    public Order orderPayment(Order order) {
        int totalPrice = order.getPrice() * order.getQuantity();

        // 재고확인 후 차감
        productService.decreaseProductWithPessimisticLock(order.getProductId(), order.getQuantity());

        // 유저확인 후 포인트 차감
        userService.decreaseUserPointWithPessimisticLock(order.getUserId(), totalPrice);

        // 주문 생성
        Order newOrder = orderService.createOrder(order.getUserId(), order.getProductId(), order.getQuantity(), order.getPrice());

        // 결제 요청
        paymentService.sendPayment();

        // 이벤트 발행
        eventPublisher.publishEvent(new Payment());

        return newOrder;
    }

//    @Transactional
//    public Order orderPaymentPessimistic(Order order) {
//        int totalPrice = order.getPrice() * order.getQuantity();
//
//        // 재고확인 후 차감 (비관적 락 사용)
//        productService.decreaseProductWithPessimisticLock(order.getProductId(), order.getQuantity());
//
//        // 유저확인 후 포인트 차감 (비관적 락 사용)
//        userService.decreaseUserPointWithPessimisticLock(order.getUserId(), totalPrice);
//
//        // 주문 생성
//        Order newOrder = orderService.createOrder(order.getUserId(), order.getProductId(), order.getQuantity(), order.getPrice());
//
//        // 결제 요청
//        paymentService.sendPayment();
//
//
//        eventPublisher.publish(new orderPaidEvent());
//
//        // 외부 데이터 수집 플랫폼으로 데이터 전송
//        dataPlatformClient.sendDataPlatform();
//
//
//
//        외부데이터 수집 플랫폼으로 데이터 전송
//
//        return newOrder;
//    }

//    private Order executeOrderPayment(Order order) {
//        int totalPrice = order.getPrice() * order.getQuantity();
//
//        // 재고확인 후 차감
//        productService.decreaseProduct(order.getProductId(), order.getQuantity());
//
//        // 유저확인 후 포인트 차감
//        userService.decreaseUserPoint(order.getUserId(), totalPrice);
//
//        // 주문 생성
//        Order newOrder = orderService.createOrder(order.getUserId(), order.getProductId(), order.getQuantity(), order.getPrice());
//
//
////        오더 + 재고차감
////        결제요청 + 포인트차감
//        // 결제 요청
//        paymentService.sendPayment();
//
//        eventPublisher.publishEvent(new Payment());
//
//        return newOrder;
//    }
}
