package hhplus.ecommoerce.infra.spring;

import hhplus.ecommoerce.biz.application.domain.event.OrderEvent;
import hhplus.ecommoerce.biz.application.domain.event.OrderEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderSpringEventPublisher implements OrderEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publisher(OrderEvent event) {
        log.info("OrderEventPublisher publisher");
        eventPublisher.publishEvent(event);
    }
}
