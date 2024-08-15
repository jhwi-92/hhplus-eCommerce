package hhplus.ecommoerce.infra.kafka;

import hhplus.ecommoerce.biz.application.domain.OrderMessagePublisher;
import hhplus.ecommoerce.biz.application.domain.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderMessageProducer implements OrderMessagePublisher {

    static final String TOPIC_NAME = "order_topic";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void send(OrderEvent event) {
        log.info("OrderMessageProducer send!!!!!!");
        kafkaTemplate.send(TOPIC_NAME, String.valueOf(event.order().getId()), event.order());

    }
}
