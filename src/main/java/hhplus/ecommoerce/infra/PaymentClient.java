package hhplus.ecommoerce.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
public class PaymentClient {

    public boolean sendPayment(Long id) {
        return true;
    }



}
