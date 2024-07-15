package hhplus.ecommoerce.infra;

import org.springframework.stereotype.Component;

@Component
public interface PaymentClient {

    boolean sendPayment();



}
