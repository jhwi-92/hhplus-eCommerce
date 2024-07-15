package hhplus.ecommoerce.service.validator;

import hhplus.ecommoerce.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    public void validator(Product product, int quantity) {

        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
    }

}
