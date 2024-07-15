package hhplus.ecommoerce.api.controller;

import hhplus.ecommoerce.api.controller.dto.ProductDTO;
import hhplus.ecommoerce.biz.application.domain.entity.Product;
import hhplus.ecommoerce.biz.application.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Product", description = "Product API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    //상품리스트 조회
    @Operation(summary = "상품 리스트 조회", description = "모든 상품 리스트를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ProductDTO.class)))
    @GetMapping
    public ResponseEntity<Object> selectProductsList() {

        List<Product> products = productService.selectProductsList();

        List<ProductDTO> productDTOs = ProductDTO.fromList(products);
        return ResponseEntity.ok(ProductDTO.ofList(productDTOs));
    }

    //상위상품 리스트 조회
    @Operation(summary = "상위 상품 리스트 조회", description = "상위 3개의 상품 리스트를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ProductDTO.class)))
    @GetMapping("/top")
    public ResponseEntity<Object> selectProductsTopList() {


        List<Product> products = productService.selectProductsTopList();

        List<ProductDTO> productDTOs = ProductDTO.fromList(products);
        return ResponseEntity.ok(ProductDTO.ofList(productDTOs));
    }
}
