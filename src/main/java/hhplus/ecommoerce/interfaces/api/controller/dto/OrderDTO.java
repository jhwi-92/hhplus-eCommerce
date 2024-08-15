package hhplus.ecommoerce.interfaces.api.controller.dto;

import hhplus.ecommoerce.biz.application.domain.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "주문 DTO")
public record OrderDTO(
    @Schema(description = "PK ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    Long id,

    @Schema(description = "유저 ID", example = "1", required = true)
    Long userId,

    @Schema(description = "상품 ID", example = "1", required = true)
    Long productId,

    @Schema(description = "개수", example = "10", required = true)
    Integer quantity,

    @Schema(description = "상품 가격", example = "100", required = true)
    Integer price,

    @Schema(description = "주문 상태", example = "성공", accessMode = Schema.AccessMode.READ_ONLY)
    String status,

    @Schema(description = "등록 일시", example = "2023-01-01T00:00:00", accessMode = Schema.AccessMode.READ_ONLY)
    LocalDateTime createdAt,

    @Schema(description = "수정 일시", example = "2023-01-01T00:00:00", accessMode = Schema.AccessMode.READ_ONLY)
    LocalDateTime updatedAt
) {
    // 필요한 경우 생성자나 정적 팩토리 메서드를 추가할 수 있습니다.
    public OrderDTO(Long id, Long userId, Long productId, Integer quantity, Integer price, String status) {
        this(id, userId, productId, quantity, price, status, null, null);
    }

    // DTO를 Entity로 변환
    public Order toEntity() {
        return new Order(
            this.userId,
            this.productId,
            this.quantity,
            this.price,
            this.status
        );
    }

    // Entity를 DTO로 변환
    public static OrderDTO from(Order order) {
        return new OrderDTO(
            order.getId(),
            order.getUserId(),
            order.getProductId(),
            order.getQuantity(),
            order.getPrice(),
            order.getStatus(),
            order.getCreatedAt(),
            order.getUpdatedAt()
        );
    }
}