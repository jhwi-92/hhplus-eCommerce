package hhplus.ecommoerce.repository;

import hhplus.ecommoerce.entity.Product;
import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findProductTopList();

}
