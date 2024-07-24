package hhplus.ecommoerce.biz.application.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ORDERS")
@Schema(description = "Order entity")
public class Order {

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

    @Schema(description = "상품 가격", example = "100")
    Integer price;

    @Schema(description = "주문 상태", example = "성공")
    String status;

    @Schema(description = "등록 일시", example = "2023-01-01T00:00:00")
    LocalDateTime createdAt;

    @Schema(description = "수정 일시", example = "2023-01-01T00:00:00")
    LocalDateTime updatedAt;

    public Order(Long userId, Long productId, Integer quantity, Integer price,
        String status) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

}
