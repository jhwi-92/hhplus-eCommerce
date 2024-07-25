package hhplus.ecommoerce.biz.application.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Cart entity")
@NoArgsConstructor
@Entity
@Data
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "PK ID", example = "1")
    Long id;

    @Schema(description = "유저 ID", example = "1")
    Long userId;

    @Schema(description = "상품 ID", example = "1")
    Long productId;

    @Schema(description = "개수", example = "10")
    Integer quantity;

    @Schema(description = "등록 일시", example = "2023-01-01T00:00:00")
    LocalDateTime createdAt;

    public Cart(Long id, Long userId, Long productId, Integer quantity, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

}