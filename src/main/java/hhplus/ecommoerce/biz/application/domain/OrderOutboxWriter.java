package hhplus.ecommoerce.biz.application.domain;

import hhplus.ecommoerce.biz.application.domain.event.OrderEvent;

public interface OrderOutboxWriter {

    void save(OrderEvent event);

    void complate(String message);

}
