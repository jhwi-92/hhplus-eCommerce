package hhplus.ecommoerce.service;


import hhplus.ecommoerce.entity.Product;
import hhplus.ecommoerce.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


    public List<Product> selectProductsList() {
        List<Product> productList = productRepository.findAll();

        return productList;
    }

    public List<Product> selectProductsTopList() {
        List<Product> productTopList = productRepository.findProductTopList();

        return productTopList;
    }

}
