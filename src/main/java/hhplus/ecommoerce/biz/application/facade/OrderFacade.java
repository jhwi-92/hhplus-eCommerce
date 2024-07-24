package hhplus.ecommoerce.biz.application.facade;

import hhplus.ecommoerce.biz.application.domain.entity.Order;
import hhplus.ecommoerce.biz.application.domain.entity.Product;
import hhplus.ecommoerce.biz.application.domain.entity.User;
import hhplus.ecommoerce.biz.application.domain.service.DataPlatformService;
import hhplus.ecommoerce.biz.application.domain.service.OrderService;
import hhplus.ecommoerce.biz.application.domain.service.PaymentService;
import hhplus.ecommoerce.biz.application.domain.service.ProductService;
import hhplus.ecommoerce.biz.application.domain.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderFacade {

    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final DataPlatformService dataPlatformService;

    //주문결제
    //현재 분리하기 좀 애매해서 락에 대해서 공부하고 추후에 @EventListener사용하여 분리할 수정예정입니다
    @Transactional
    public Order orderPayment(Order order) {

        int totalPrice = order.getPrice() * order.getQuantity();

        //재고확인 후 차감
        productService.decreaseProduct(order.getProductId(), order.getQuantity());

        //유저확인 후 포인트 차감
        userService.decreaseUserPoint(order.getUserId(), totalPrice);

        //주문 생성
        Order newOrder = orderService.createOrder(order.getUserId(), order.getProductId(), order.getQuantity(), order.getPrice());

        //결제 요청
        paymentService.sendPayment();

        //TODO
        //외부 데이터 수집 플랫폼으로 데이터 전송
        //스텁으로 서비스 한개 만들어서 우선 사용
        dataPlatformService.sendDataPlatform();
        return newOrder;
    }
}
