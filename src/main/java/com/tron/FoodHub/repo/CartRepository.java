package com.tron.FoodHub.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tron.FoodHub.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
