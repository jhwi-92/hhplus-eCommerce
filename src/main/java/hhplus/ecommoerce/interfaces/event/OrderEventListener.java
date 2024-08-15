package hhplus.ecommoerce.interfaces.event;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;
import static org.springframework.transaction.event.TransactionPhase.BEFORE_COMMIT;

import hhplus.ecommoerce.biz.application.domain.OrderMessagePublisher;
import hhplus.ecommoerce.biz.application.domain.OrderOutboxWriter;
import hhplus.ecommoerce.biz.application.domain.event.OrderDataMessage;
import hhplus.ecommoerce.biz.application.domain.event.OrderEvent;
import hhplus.ecommoerce.infra.DataPlatformClientImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderEventListener {

    // 외부 API 에 쏘는 구현체가 들어올 거
    private final DataPlatformClientImpl dataPlatformClient; // domain ( IF )
    // 카프카 발행하는 구현체가 들어올 거
    private final OrderMessagePublisher orderMessagePublisher; // domain (IF)
    // 아웃박스에 저장하는 거가 들어올 거
    private final OrderOutboxWriter outboxWriter; // domain (IF)

    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    void sendReservationMessage(OrderEvent event) {
        dataPlatformClient.send(OrderDataMessage.from(event));
    }
    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    void publishMessage(OrderEvent event) {
        orderMessagePublisher.send(event);
    }

    @TransactionalEventListener(phase = BEFORE_COMMIT)
    void saveOutbox(OrderEvent event) {
        outboxWriter.save(event);
    }
}