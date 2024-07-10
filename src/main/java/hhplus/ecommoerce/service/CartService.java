package hhplus.ecommoerce.service;

import hhplus.ecommoerce.entity.Cart;
import hhplus.ecommoerce.repository.CartRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    public List<Cart> selectCartList(Long userId) {
        List<Cart> cartList = cartRepository.findByUserId(userId);

        return cartList;
    }



}
