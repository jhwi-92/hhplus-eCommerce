package hhplus.ecommoerce.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {

    @GetMapping("/{userId}")
    public ResponseEntity<Object> selectCartList(@PathVariable String userId) {
        Map<String, Object> responseData = new HashMap<>();

        responseData.put("cartId", 1);
        responseData.put("productId", 1);
        responseData.put("name", "이강주");
        responseData.put("price", 32000);
        responseData.put("quantity", 1);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Object> addCart() {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", "추가되었습니다");

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/{userId}/remove")
    public ResponseEntity<Object> removeCart() {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", "삭제되었습니다");

        return ResponseEntity.ok(responseData);
    }
}
