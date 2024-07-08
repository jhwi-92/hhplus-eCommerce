package hhplus.ecommoerce.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "User entity")
public record User(

    @Schema(description = "PK ID", example = "1")
    Long id,

    @Schema(description = "유저 명", example = "이석범")
    String name,

    @Schema(description = "유저 포인트", example = "100")
    Integer point,

    @Schema(description = "등록 일시", example = "2023-01-01T00:00:00")
    LocalDateTime createdAt
) {}