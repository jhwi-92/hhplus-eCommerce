package hhplus.ecommoerce.interfaces.consumer;

import hhplus.ecommoerce.biz.application.domain.OrderOutboxWriter;
import hhplus.ecommoerce.biz.application.domain.entity.Order;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final OrderOutboxWriter outboxWriter;
    @KafkaListener(topics = "order_topic", groupId = "order_group")
    public void consume(Order message) throws IOException {
        log.info("Consumed message : {}", message);
        //outbox update -> "PUBLISHED"
        outboxWriter.complate("test");
    }
}