package hhplus.ecommoerce.biz.application.domain.event;

import hhplus.ecommoerce.biz.application.domain.entity.Order;

public record OrderDataMessage(
    String orderId,
    Long userId,
    Long productId,
    int quantity,
    int price,
    String status
) {
    public static OrderDataMessage from(OrderEvent event) {
        Order order = event.order();
        return new OrderDataMessage(
            order.getId().toString(),
            order.getUserId(),
            order.getProductId(),
            order.getQuantity(),
            order.getPrice(),
            order.getStatus()
        );
    }
}