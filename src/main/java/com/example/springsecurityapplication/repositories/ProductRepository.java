package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByTitle(String title);
}
