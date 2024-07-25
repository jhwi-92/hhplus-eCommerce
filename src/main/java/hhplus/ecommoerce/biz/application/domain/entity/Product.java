package hhplus.ecommoerce.biz.application.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//    @Version
//    private Integer version = 0;  // 버전 필드 추가

    public Product(String name, Integer price, Integer quantity, LocalDateTime createdAt) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = createdAt;
    }

    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

}

