package hhplus.ecommoerce.biz.application.domain.service;

import hhplus.ecommoerce.biz.application.domain.entity.Order;
import hhplus.ecommoerce.biz.application.domain.repository.OrderRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(Long userId, Long productId, Integer quantity, Integer price) {
        Order newOrder = new Order(
            userId,
            productId,
            quantity,
            price,
            "성공"
        );

        return orderRepository.save(newOrder);
    }

}
