package hhplus.ecommoerce.infra;

import hhplus.ecommoerce.biz.application.domain.DataPlatformClient;
import hhplus.ecommoerce.biz.application.domain.event.OrderDataMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataPlatformClientImpl implements DataPlatformClient {

    public void send(OrderDataMessage payment) {
        log.info("DataPlatForm Send!!!!!");
    }



}
