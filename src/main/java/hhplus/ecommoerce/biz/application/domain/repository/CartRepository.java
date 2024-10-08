package hhplus.ecommoerce.biz.application.domain.repository;

import hhplus.ecommoerce.biz.application.domain.entity.Cart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);


}
