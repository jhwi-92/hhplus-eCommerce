package hhplus.ecommoerce.biz.application.domain.repository;


import hhplus.ecommoerce.biz.application.domain.entity.Product;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ProductRepositoryImpl implements ProductRepositoryCustom {


    @Override
    public List<Product> findProductTopList() {

        List<Product> expectedProducts = Arrays.asList(
            new Product(1L, "윤용한", 1, 3700, LocalDateTime.now()),
            new Product(2L, "아이유", 2, 980000000, LocalDateTime.now())
        );
        return expectedProducts;
    }
}
