package hhplus.ecommoerce.repository;


import hhplus.ecommoerce.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findProductTopList() {


        List<Product> products = List.of(
            new Product(1L, "이강주", 328000, 1, null),
            new Product(2L, "박지용", 48000, 1, null),
            new Product(3L, "백현명", 59000, 1, null),
            new Product(4L, "윤용한", 2800, 1, null),
            new Product(5L, "이석범", 45000, 1, null)
        );

        return products;
    }
}
