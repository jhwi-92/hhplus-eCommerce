package hhplus.ecommoerce.service;


import hhplus.ecommoerce.entity.Product;
import hhplus.ecommoerce.repository.ProductRepository;
import hhplus.ecommoerce.service.validator.ProductValidator;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductValidator productValidator;

    public List<Product> selectProductsList() {
        List<Product> productList = productRepository.findAll();

        return productList;
    }

    public List<Product> selectProductsTopList() {
        List<Product> productTopList = productRepository.findProductTopList();

        return productTopList;
    }

    public Product getProduct(Long productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("상품정보를 찾을 수 없습니다."));

        return product;
    }


    @Transactional
    public void decreaseProduct(Product product, int quantity) {
        productValidator.validator(product, quantity);
        product.decreaseQuantity(quantity);
        productRepository.save(product);

    }


}
