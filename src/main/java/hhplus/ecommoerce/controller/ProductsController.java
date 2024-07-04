package hhplus.ecommoerce.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductsController {

    //상품리스트 조회
    @GetMapping
    public ResponseEntity<Object> selectProductsList() {

        // 리스트를 만들어서 데이터 추가
        List<Map<String, Object>> productList = new ArrayList<>();

        // 상품 정보 1 추가
        Map<String, Object> product1 = new HashMap<>();
        product1.put("productId", 1);
        product1.put("name", "이강주");
        product1.put("price", 32000);
        productList.add(product1);

        return ResponseEntity.ok(productList);
    }

    //상위상품 리스트 조회
    @GetMapping("/top")
    public ResponseEntity<Object> selectProductsTopList() {

        List<Map<String, Object>> productList = new ArrayList<>();

        Map<String, Object> product1 = new HashMap<>();
        product1.put("productId", 1);
        product1.put("name", "이강주");
        product1.put("price", 32000);
        productList.add(product1);

        Map<String, Object> product2 = new HashMap<>();
        product2.put("productId", 2);
        product2.put("name", "박지용");
        product2.put("price", 48000);
        productList.add(product2);

        Map<String, Object> product3 = new HashMap<>();
        product3.put("productId", 3);
        product3.put("name", "백현명");
        product3.put("price", 59000);
        productList.add(product3);
        return ResponseEntity.ok(productList);
    }
}
