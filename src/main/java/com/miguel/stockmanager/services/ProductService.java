package com.miguel.stockmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.miguel.stockmanager.models.ProductModel;
import com.miguel.stockmanager.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

  final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional
  public ProductModel save(ProductModel productModel) {
    return productRepository.save(productModel);
  }

  public List<ProductModel> findAll() {
    return productRepository.findAll();
  }

  public Optional<ProductModel> findByName(String name) {
    return productRepository.findByName(name);
  }

  @Transactional
  public void delete(ProductModel productModel) {
    productRepository.delete(productModel);
  }

  public Optional<ProductModel> findById(Long id) {
    return productRepository.findById(id);
  }
}
