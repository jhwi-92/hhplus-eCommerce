package hhplus.ecommoerce.biz.application.eventlisteners;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import hhplus.ecommoerce.biz.application.domain.entity.Payment;
import hhplus.ecommoerce.infra.DataPlatformClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderPaidEventListener {

    private final DataPlatformClient dataPlatformClient;

    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void sendOrderInfo(Payment payment) {
        try {
            dataPlatformClient.sendDataPlatform(payment);
        } catch(Exception e) {
            log.error("주문정보전달 실패");
        }
    }
}
