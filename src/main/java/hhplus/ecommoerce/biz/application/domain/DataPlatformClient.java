package hhplus.ecommoerce.biz.application.domain;

import hhplus.ecommoerce.biz.application.domain.event.OrderDataMessage;

public interface DataPlatformClient {
    void send(OrderDataMessage message);
}