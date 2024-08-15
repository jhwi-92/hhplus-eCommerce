package hhplus.ecommoerce.infra;

//import com.fasterxml.jackson.databind.ObjectMapper;
import hhplus.ecommoerce.biz.application.domain.OrderOutboxWriter;
import hhplus.ecommoerce.biz.application.domain.entity.Outbox;
import hhplus.ecommoerce.biz.application.domain.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderOutboxWriterImpl implements OrderOutboxWriter {

//    private final OutboxRepository outboxRepository;
//    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void save(OrderEvent event) {
        log.info("OrderOutBox save!!");
//        log.info("Saving order event to outbox: {}", event);
//        try {
//            String payload = objectMapper.writeValueAsString(event);
//            Outbox outboxMessage = new Outbox(
//                "ORDER",
//                event.order().getId().toString(),
//                "INIT",
//                payload
//            );
////            outboxRepository.save(outboxMessage);
//            log.info("Order event saved to outbox successfully");
//        } catch (Exception e) {
//            log.error("Failed to save order event to outbox", e);
//            throw new RuntimeException("Failed to save order event to outbox", e);
//        }log.info("outbox INIT save");
    }

    @Override
    public void complate(String message) {
        log.info("outbox PUBLISHED update");
    }
}