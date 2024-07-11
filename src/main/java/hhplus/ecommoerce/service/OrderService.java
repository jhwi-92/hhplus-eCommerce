package hhplus.ecommoerce.service;

import hhplus.ecommoerce.entity.Order;
import hhplus.ecommoerce.repository.OrderRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(Long userId, Long productId, Integer quantity, Integer price) {
        Order newOrder = new Order(
            null,  // id는 자동 생성될 것이므로 null
            userId,
            productId,
            quantity,
            price,
            "성공",  // 상태를 "성공"으로 설정
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        return orderRepository.save(newOrder);
    }

}
