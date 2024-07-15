package hhplus.ecommoerce.biz.application.domain.service;

import hhplus.ecommoerce.biz.application.domain.entity.Cart;
import hhplus.ecommoerce.biz.application.domain.repository.CartRepository;
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
