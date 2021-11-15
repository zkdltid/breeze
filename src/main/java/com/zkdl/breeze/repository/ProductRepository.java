package com.zkdl.breeze.repository;

import com.zkdl.breeze.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findAllByProduct(Product product);
    Optional<Product> findById(Long id);
//    Optional<Product> findByCategoryId(Category category);
}
