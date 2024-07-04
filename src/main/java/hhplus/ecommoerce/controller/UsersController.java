package hhplus.ecommoerce.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {

    //잔액 조회
    @GetMapping("/point/{userId}")
    public ResponseEntity<Object> selectUserPointList() {
        Map<String, Object> responseData = new HashMap<>();

        responseData.put("userId", 1);
        responseData.put("point", 5000);
        return ResponseEntity.ok(responseData);
    }

    //잔액 충전
    @PostMapping("/point/charge")
    public ResponseEntity<Object> userPointCharge() {
        Map<String, Object> responseData = new HashMap<>();

        responseData.put("userId", 1);
        responseData.put("point", 1000000);

        return ResponseEntity.ok(responseData);
    }
}
