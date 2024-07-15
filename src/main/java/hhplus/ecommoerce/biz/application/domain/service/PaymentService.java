package hhplus.ecommoerce.biz.application.domain.service;

import hhplus.ecommoerce.infra.PaymentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentClient paymentClient;


    public boolean sendPayment() {
        paymentClient.sendPayment();
        return true;
    }

}
