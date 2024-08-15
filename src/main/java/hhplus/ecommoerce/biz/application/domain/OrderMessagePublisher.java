package hhplus.ecommoerce.biz.application.domain;

import hhplus.ecommoerce.biz.application.domain.event.OrderEvent;

public interface OrderMessagePublisher {
    //주문정보를
    void send(OrderEvent order);

}
