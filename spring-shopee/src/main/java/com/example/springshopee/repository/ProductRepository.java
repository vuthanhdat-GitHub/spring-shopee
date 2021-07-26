package com.example.springshopee.repository;

import com.example.springshopee.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product , String> {
}
