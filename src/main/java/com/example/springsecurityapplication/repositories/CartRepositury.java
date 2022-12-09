package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepositury extends JpaRepository<Cart, Integer> {
    List<Cart> findByPersonId(int id);
}
