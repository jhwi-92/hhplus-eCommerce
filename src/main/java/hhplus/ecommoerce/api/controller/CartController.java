package hhplus.ecommoerce.api.controller;

import hhplus.ecommoerce.api.controller.dto.CartDTO;
import hhplus.ecommoerce.biz.application.domain.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cart", description = "Cart API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Operation(summary = "장바구니 리스트 조회", description = "장바구니 리스트 조회기능")
    @ApiResponse(responseCode = "200", description = "조회 성공",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = CartDTO.class)))
    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> selectCartList(@PathVariable Long userId) {


        List<CartDTO> cartItems = CartDTO.fromList(cartService.selectCartList(userId));
        return ResponseEntity.ok(CartDTO.ofList(cartItems));
    }

    @Operation(summary = "장바구니 추가", description = "장바구니 상품 추가기능")
    @ApiResponse(responseCode = "200", description = "추가 성공",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = CartDTO.class)))
    @PostMapping("/{userId}/add")
    public ResponseEntity<CartDTO> addCart(@Parameter(description = "유저ID") @PathVariable Long userId,
        @RequestBody CartDTO request) {
        CartDTO addedItem = new CartDTO(1L, userId, request.productId(), request.productName(), request.price(), request.quantity());
        return ResponseEntity.ok(addedItem);
    }

    @Operation(summary = "장바구니 삭제", description = "장바구니 상품 삭제기능")
    @ApiResponse(responseCode = "200", description = "삭제 성공",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = CartDTO.class)))
    @PostMapping("/{userId}/remove")
    public ResponseEntity<CartDTO> removeCart(@Parameter(description = "유저 ID") @PathVariable Long userId,
        @Parameter(description = "장바구니 상 ID") @RequestParam Long cartItemId) {

        List<CartDTO> remainingItems = List.of(
            new CartDTO(cartItemId, userId, 2L, "다른 상품", 25000, 1)
        );
        return ResponseEntity.ok(CartDTO.ofList(remainingItems));
    }
}
