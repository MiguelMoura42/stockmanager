package com.miguel.stockmanager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miguel.stockmanager.models.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
  Optional<ProductModel> findByName(String name);
}
