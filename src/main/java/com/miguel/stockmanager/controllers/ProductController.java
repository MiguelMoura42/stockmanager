package com.miguel.stockmanager.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguel.stockmanager.dtos.ProductDto;
import com.miguel.stockmanager.models.ProductModel;
import com.miguel.stockmanager.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

  final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductDto productDto) {
    var productModel = new ProductModel();
    BeanUtils.copyProperties(productDto, productModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
  }

  @GetMapping
  public ResponseEntity<List<ProductModel>> getAllProducts() {
    return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
    Optional<ProductModel> productModelOptional = productService.findById(id);
    if (!productModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
    }
    return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteOneProduct(@PathVariable(value = "id") UUID id) {
    Optional<ProductModel> productModelOptional = productService.findById(id);
    if (!productModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
    }
    productService.delete(productModelOptional.get());
    return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfully!");
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> replaceNameProduct(@PathVariable(value = "id") UUID id,
      @RequestBody @Valid ProductDto productDto) {
    Optional<ProductModel> productModelOptional = productService.findById(id);
    if (!productModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
    }
    var productModel = new ProductModel();
    BeanUtils.copyProperties(productDto, productModel);
    productModel.setId(productModelOptional.get().getId());
    productModel.setQuantity(productModelOptional.get().getQuantity());
    productModel.setCreatedAt(productModelOptional.get().getCreatedAt());
    return ResponseEntity.status(HttpStatus.OK).body(productService.save(productModel));
  }
}
