package hhplus.ecommoerce.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "OrderHistory entity")
public record OrderHistory(

    @Schema(description = "PK ID", example = "1")
    Long id,

    @Schema(description = "주문 ID", example = "1")
    Long orderId,

    @Schema(description = "상품 ID", example = "1")
    Long productId,
    @Schema(description = "상품 가격", example = "100")
    Integer price,

    @Schema(description = "주문 상태", example = "성공")
    String status,

    @Schema(description = "등록 일시", example = "2023-01-01T00:00:00")
    LocalDateTime createdAt
) {}
