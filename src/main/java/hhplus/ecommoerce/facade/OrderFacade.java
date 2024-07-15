package hhplus.ecommoerce.facade;

import hhplus.ecommoerce.controller.dto.OrderDTO;
import hhplus.ecommoerce.entity.Order;
import hhplus.ecommoerce.entity.Product;
import hhplus.ecommoerce.entity.User;
import hhplus.ecommoerce.service.DataPlatformService;
import hhplus.ecommoerce.service.OrderService;
import hhplus.ecommoerce.service.PaymentService;
import hhplus.ecommoerce.service.ProductService;
import hhplus.ecommoerce.service.UserService;
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
    @Transactional
    public Order orderPayment(Order order) {

        int totalPrice = order.price() * order.quantity();

        //서비스의 흐름

        //상품정보 조회 - 유효한상품
        Product product = productService.getProduct(order.productId());

        //유저정보 조회 - 유효한유저 / 잔액 확인 및 차감
        User user = userService.selectUser(order.userId());

        //재고 차감
        productService.decreaseProduct(product, order.quantity());

        //유저 포인트 차감
        userService.decreaseUserPoint(user, totalPrice);

        //주문 생성
        Order newOrder = orderService.createOrder(user.getId(), product.getId(), order.quantity(), product.getPrice());

        //결제 요청
        //설계부족,,,
        boolean paymentResult = paymentService.sendPayment();

        if(!paymentResult) {
            //익셉션 커스텀으로 변경할 것
            throw new IllegalArgumentException("결제에 실패했습니다.");
        }

        //TODO
        //외부 데이터 수집 플랫폼으로 데이터 전송
        //스텁으로 서비스 한개 만들어서 우선 사용
        dataPlatformService.sendDataPlatform();
        return newOrder;
    }

}
