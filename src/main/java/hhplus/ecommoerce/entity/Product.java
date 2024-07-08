package hhplus.ecommoerce.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Product entity")
public record Product(

    @Schema(description = "PK ID", example = "1")
    Long id,

    @Schema(description = "상품 명", example = "김한결")
    String name,

    @Schema(description = "상품 가격", example = "100")
    Integer price,

    @Schema(description = "등록 일시", example = "2023-01-01T00:00:00")
    LocalDateTime createdAt
) {}
