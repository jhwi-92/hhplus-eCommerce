package hhplus.ecommoerce.biz.application.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "User entity")
@NoArgsConstructor
@Entity
@Getter
@Table(name = "USER")
public class User {

    @Id
    @Schema(description = "PK ID", example = "1")
    Long id;

    @Schema(description = "유저 명", example = "이석범")
    String name;

    @Schema(description = "유저 포인트", example = "100")
    Integer point;

    @Schema(description = "등록 일시", example = "2023-01-01T00:00:00")
    LocalDateTime createdAt;

    public void chargePoint(int point) {
        this.point += point;
    }

    public User(Long id, String name, Integer point, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.createdAt = createdAt;
    }

    public void decreasePoint(int point) {

        this.point -= point;
    }


}