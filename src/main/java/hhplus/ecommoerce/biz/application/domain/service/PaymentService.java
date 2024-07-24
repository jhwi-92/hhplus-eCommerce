package hhplus.ecommoerce.biz.application.domain.service;

import hhplus.ecommoerce.infra.PaymentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentClient paymentClient;


    //결제는 pg요청으로 생각해서 현재는 무조건 true로 받고 있습니다
    public void sendPayment() {

        if(!paymentClient.sendPayment(1L)) {
            throw new IllegalArgumentException("결제에 실패했습니다.");
        }
    }

}
