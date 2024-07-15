package hhplus.ecommoerce.biz.application.domain.repository;

import hhplus.ecommoerce.biz.application.domain.entity.Product;
import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findProductTopList();

}
