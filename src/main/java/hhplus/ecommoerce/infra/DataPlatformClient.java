package hhplus.ecommoerce.infra;

import hhplus.ecommoerce.biz.application.domain.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class DataPlatformClient {

    public boolean sendDataPlatform(Payment payment) {
        return true;
    }



}
