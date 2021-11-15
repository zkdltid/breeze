package com.zkdl.breeze.repository;

import com.zkdl.breeze.model.CartItem;
import com.zkdl.breeze.model.User;
import com.zkdl.breeze.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    Boolean existsByUserAndProduct(User user, Product product);
    Optional<CartItem> findByUserAndProduct(User user, Product product);
}
