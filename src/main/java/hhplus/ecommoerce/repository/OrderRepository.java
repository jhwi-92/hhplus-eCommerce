package hhplus.ecommoerce.repository;

import hhplus.ecommoerce.entity.Cart;
import hhplus.ecommoerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
