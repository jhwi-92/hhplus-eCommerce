package hhplus.ecommoerce.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;

@Schema(description = "Product entity")
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

    @Schema(description = "등록 일시", example = "2023-01-01T00:00:00")
    LocalDateTime createdAt;

    public Product(Long id, String name, Integer price, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
    }

    public Product() {

    }
}

