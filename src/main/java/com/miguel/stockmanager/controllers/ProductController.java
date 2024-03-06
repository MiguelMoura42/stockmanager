package com.miguel.stockmanager.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miguel.stockmanager.requests.EntryRequest;
import com.miguel.stockmanager.requests.ProductRequest;
import com.miguel.stockmanager.responses.ProductResponse;
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
  public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
    var productResponse = productService.save(productRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
  }

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAllProducts() {
    return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
  }

  @GetMapping("/{name}")
  public ResponseEntity<ProductResponse> getOneProduct(@PathVariable(value = "name") String name) {
    ProductResponse product = productService.getProductByName(name);
    return ResponseEntity.status(HttpStatus.OK).body(product);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id) {
    productService.deleteProductById(id);
    return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfully!");
  }

  @PutMapping("/{productId}")
  public ResponseEntity<String> addQuantityToProduct(@RequestBody @Valid EntryRequest entryRequest,
      @PathVariable(value = "productId") Long productId) {
    try {
      productService.addQuantityToProduct(entryRequest, productId);
      return ResponseEntity.status(HttpStatus.OK).body("Quantity added successfully!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add quantity!");
    }

  }
}
