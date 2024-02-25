package com.miguel.stockmanager.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.miguel.stockmanager.models.ProductModel;
import com.miguel.stockmanager.repositories.ProductRepository;
import com.miguel.stockmanager.requests.ProductRequest;
import com.miguel.stockmanager.responses.ProductResponse;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

  final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional
  public ProductResponse save(ProductRequest productRequest) {
    var productModel = new ProductModel();
    BeanUtils.copyProperties(productRequest, productModel);
    ProductModel savedProduct = productRepository.save(productModel);
    return new ProductResponse(savedProduct);
  }

  public List<ProductResponse> findAll() {
    List<ProductModel> products = productRepository.findAll();
    return products.stream()
        .map(product -> new ProductResponse(product))
        .collect(Collectors.toList());
  }

  public ProductResponse getProductByName(String name) {
    Optional<ProductModel> optionalProductModel = productRepository.findByName(name);
    if (optionalProductModel.isPresent()) {
      ProductModel productModel = optionalProductModel.get();
      return new ProductResponse(productModel);
    } else {
      throw new RuntimeException();
    }
  }

  @Transactional
  public void deleteProductById(Long id) {
    Optional<ProductModel> optionalProductModel = productRepository.findById(id);
    if (optionalProductModel.isPresent()) {
      productRepository.deleteById(id);
    } else {
      throw new RuntimeException();
    }
  }
}
