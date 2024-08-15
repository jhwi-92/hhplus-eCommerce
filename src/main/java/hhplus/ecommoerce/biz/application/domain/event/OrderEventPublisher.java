package hhplus.ecommoerce.biz.application.domain.event;


public interface OrderEventPublisher {

    /**
     * 주문 관련 일반 이벤트를 발행합니다.
     */
    void publisher(OrderEvent event);
}
