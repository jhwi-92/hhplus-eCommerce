package hhplus.ecommoerce.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hhplus.ecommoerce.biz.application.domain.service.ProductService;
import hhplus.ecommoerce.biz.application.domain.entity.Product;
import hhplus.ecommoerce.biz.application.domain.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void selectProductsList_AllProducts() {
        // given
        List<Product> expectedProducts = Arrays.asList(
            new Product(1L, "윤용한", 1, 3700, LocalDateTime.now()),
            new Product(2L, "아이유", 2, 980000000, LocalDateTime.now())
        );
        when(productRepository.findAll()).thenReturn(expectedProducts);

        // when
        List<Product> result = productService.selectProductsList();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedProducts, result);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void selectProductsTopList_ReturnTopProducts() {
        // given
        List<Product> expectedTopProducts = Arrays.asList(
            new Product(1L, "이강주", 3988880, 2, LocalDateTime.now()),
            new Product(2L, "백현명", 3599990, 2, LocalDateTime.now()),
            new Product(2L, "백현명", 3499990, 2, LocalDateTime.now())
        );
        when(productRepository.findProductTopList()).thenReturn(expectedTopProducts);

        // when
        List<Product> result = productService.selectProductsTopList();

        // then
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(expectedTopProducts, result);
        verify(productRepository, times(1)).findProductTopList();
    }


}