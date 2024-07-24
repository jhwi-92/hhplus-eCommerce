package hhplus.ecommoerce.biz.application.domain.service;

import hhplus.ecommoerce.infra.PaymentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentClient paymentClient;


    public void sendPayment() {

        if(!paymentClient.sendPayment(1L)) {
            throw new IllegalArgumentException("결제에 실패했습니다.");
        }
    }

}
