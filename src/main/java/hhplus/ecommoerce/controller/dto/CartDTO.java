package hhplus.ecommoerce.controller.dto;

import hhplus.ecommoerce.entity.Cart;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public record CartDTO(
    @Schema(description = "Cart item ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    Long id,

    @Schema(description = "User ID", example = "100")
    Long userId,

    @Schema(description = "Product ID", example = "1001")
    Long productId,

    @Schema(description = "Product name", example = "이강주")
    String productName,

    @Schema(description = "Product price", example = "32000")
    Integer price,

    @Schema(description = "Quantity", example = "1")
    Integer quantity,

    @Schema(description = "카트 리스트")
    List<CartDTO> cartItems
) {

    // 단일 카트 아이템을 위한 생성자
    public CartDTO(Long id, Long userId, Long productId, String productName, Integer price, Integer quantity) {
        this(id, userId, productId, productName, price, quantity, null);
    }

    // 카트 목록을 위한 정적 팩토리 메서드
    public static CartDTO ofList(List<CartDTO> cartItems) {
        return new CartDTO(null, null, null, null, null, null, cartItems);
    }

    // Cart 엔티티로부터 CartDTO 생성
    public static CartDTO from(Cart cart, String productName, Integer price) {
        return new CartDTO(
            cart.id(),
            cart.userId(),
            cart.productId(),
            productName,
            price,
            cart.quantity(),
            null
        );
    }

    // Cart 엔티티 리스트로부터 CartDTO 리스트 생성
    public static List<CartDTO> fromList(List<Cart> carts, Function<Long, String> productNameGetter, Function<Long, Integer> priceGetter) {
        return carts.stream()
            .map(cart -> CartDTO.from(cart, productNameGetter.apply(cart.productId()), priceGetter.apply(cart.productId())))
            .collect(Collectors.toList());
    }

    // CartDTO로부터 Cart 엔티티 생성
    public Cart toEntity() {
        return new Cart(
            this.id,
            this.userId,
            this.productId,
            this.quantity,
            LocalDateTime.now()
        );
    }

    // CartDTO가 단일 아이템인지 목록인지 확인하는 메서드
    public boolean isList() {
        return cartItems != null;
    }
}