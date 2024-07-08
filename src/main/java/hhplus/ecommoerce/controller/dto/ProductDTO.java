package hhplus.ecommoerce.controller.dto;

import hhplus.ecommoerce.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Product DTO")
public record ProductDTO(
    @Schema(description = "상품 ID", example = "1")
    Long id,

    @Schema(description = "상품명", example = "이강주")
    String name,

    @Schema(description = "상품 가격", example = "32000")
    Integer price,

    @Schema(description = "상품 리스트")
    List<ProductDTO> products
) {
    // 단일 상품을 위한 생성자
    public ProductDTO(Long id, String name, Integer price) {
        this(id, name, price, null);
    }

    // 상품 리스트를 위한 생성자
    public static ProductDTO ofList(List<ProductDTO> products) {
        return new ProductDTO(null, null, null, products);
    }

    // Product 엔티티로부터 ProductDTO 생성
    public static ProductDTO from(Product product) {
        return new ProductDTO(product.id(), product.name(), product.price());
    }

    // Product 엔티티 리스트로부터 ProductDTO 리스트 생성
    public static List<ProductDTO> fromList(List<Product> products) {
        return products.stream()
            .map(ProductDTO::from)
            .toList();
    }
}