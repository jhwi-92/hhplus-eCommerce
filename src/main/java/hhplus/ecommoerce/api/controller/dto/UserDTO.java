package hhplus.ecommoerce.api.controller.dto;

import hhplus.ecommoerce.biz.application.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "User DTO")
public record UserDTO(
    @Schema(description = "PK ID", example = "1")
    Long id,

    @Schema(description = "유저 명", example = "이석범")
    String name,

    @Schema(description = "유저 포인트", example = "100")
    Integer point,

    @Schema(description = "등록 일시", example = "2023-01-01T00:00:00")
    LocalDateTime createdAt
) {
    public static UserDTO from(User user) {
        return new UserDTO(
            user.getId(),
            user.getName(),
            user.getPoint(),
            user.getCreatedAt()
        );
    }

    public User toEntity() {
        return new User(
            this.id,
            this.name,
            this.point,
            LocalDateTime.now()
        );
    }
}