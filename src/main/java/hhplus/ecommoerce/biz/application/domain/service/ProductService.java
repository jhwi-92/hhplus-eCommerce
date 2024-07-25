package hhplus.ecommoerce.biz.application.domain.service;


import hhplus.ecommoerce.biz.application.domain.service.validator.ProductValidator;
import hhplus.ecommoerce.biz.application.domain.entity.Product;
import hhplus.ecommoerce.biz.application.domain.repository.ProductRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public Product getProduct(Long productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("상품정보를 찾을 수 없습니다."));

        return product;
    }


    public void decreaseProduct(long productId, int quantity) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("상품정보를 찾을 수 없습니다."));

        //validator을 만든 이유가 검증할 게 더 있을 것 같아서 만들었는데 사용할게 우선 없어서 삭제하기도 좀 그래서 일단 사용하고있습니다..
        productValidator.validator(product, quantity);
        product.decreaseQuantity(quantity);
        productRepository.save(product);
    }

    public void decreaseProductWithPessimisticLock(long productId, int quantity) {

        Product product = productRepository.findByIdWithPessimisticLock(productId).orElseThrow(() -> new IllegalArgumentException("상품정보를 찾을 수 없습니다."));

        //validator을 만든 이유가 검증할 게 더 있을 것 같아서 만들었는데 사용할게 우선 없어서 삭제하기도 좀 그래서 일단 사용하고있습니다..
        productValidator.validator(product, quantity);
        product.decreaseQuantity(quantity);
        productRepository.save(product);
    }


}
