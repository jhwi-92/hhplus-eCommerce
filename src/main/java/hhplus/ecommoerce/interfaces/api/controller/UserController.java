package hhplus.ecommoerce.interfaces.api.controller;

import hhplus.ecommoerce.interfaces.api.controller.dto.UserDTO;
import hhplus.ecommoerce.biz.application.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "Users API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    //잔액 조회
    @Operation(summary = "잔액 조회", description = "유저의 현재 포인트를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "잔액 조회 성공",
        content = @Content(schema = @Schema(implementation = UserDTO.class)))
    @GetMapping("/point/{userId}")
    public ResponseEntity<UserDTO> selectUserPoint(@PathVariable Long userId) {
        // 실제 서비스 로직 구현 필요
        return ResponseEntity.ok(new UserDTO(userId, "이석범", 5000, LocalDateTime.now()));
    }


    //잔액 충전
    @Operation(summary = "잔액 충전", description = "유저의 포인트를 충전합니다.")
    @ApiResponse(responseCode = "200", description = "잔액 충전 성공",
        content = @Content(schema = @Schema(implementation = UserDTO.class)))
    @PostMapping("/point/charge")
    public ResponseEntity<UserDTO> useChargePoint(@RequestBody UserDTO userDto) {
        // 실제 서비스 로직 구현 필요
        return ResponseEntity.ok(new UserDTO(userDto.id(), userDto.name(), 1000000, LocalDateTime.now()));
    }
}
