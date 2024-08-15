package hhplus.ecommoerce.biz.application.domain.event;

import hhplus.ecommoerce.biz.application.domain.entity.Order;

public record OrderEvent(
    Order order
) {
}
