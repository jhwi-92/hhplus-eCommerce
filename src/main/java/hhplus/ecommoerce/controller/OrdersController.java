package hhplus.ecommoerce.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrdersController {

    //주문결제 요청
    @PostMapping("/payment")
    public ResponseEntity<Object> ordersPayment() {
        Map<String, Object> responseData = new HashMap<>();

        responseData.put("message", "성공적으로 결제되었습니다.");
        return ResponseEntity.ok(responseData);
    }

}
