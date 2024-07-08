package hhplus.ecommoerce.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Order entity")
public record Order(

    @Schema(description = "PK ID", example = "1")
    Long id,

    @Schema(description = "유저 ID", example = "1")
    Long userId,

    @Schema(description = "상품 ID", example = "1")
    Long productId,

    @Schema(description = "개수", example = "10")
    Integer quantity,

    @Schema(description = "상품 가격", example = "100")
    Integer price,

    @Schema(description = "주문 상태", example = "성공")
    String status,

    @Schema(description = "등록 일시", example = "2023-01-01T00:00:00")
    LocalDateTime createdAt,

    @Schema(description = "수정 일시", example = "2023-01-01T00:00:00")
    LocalDateTime updatedAt
) {}
