package hhplus.ecommoerce.biz.application.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Product entity")
@NoArgsConstructor
@Entity
@Getter
@Table(name = "PRODUCT")
public class Product {

    @Id
    @Schema(description = "PK ID", example = "1")
    Long id;

    @Schema(description = "상품 명", example = "김한결")
    String name;

    @Schema(description = "상품 가격", example = "100")
    Integer price;

    @Schema(description = "상품 개수", example = "10")
    Integer quantity;

    @Schema(description = "등록 일시", example = "2023-01-01T00:00:00")
    LocalDateTime createdAt;

    public Product(Long id, String name, Integer price, Integer quantity, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = createdAt;
    }

    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

}

