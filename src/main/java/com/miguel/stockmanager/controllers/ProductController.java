package com.miguel.stockmanager.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguel.stockmanager.requests.ProductRequest;
import com.miguel.stockmanager.responses.ProductResponse;
import com.miguel.stockmanager.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "Controller to products")
public class ProductController {

  final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  @Operation(summary = "Creates a new product")
  @ApiResponse(responseCode = "201")
  public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
    var productResponse = productService.save(productRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
  }

  @GetMapping
  @Operation(summary = "Get all products")
  public ResponseEntity<List<ProductResponse>> getAllProducts() {
    return ResponseEntity.ok().body(productService.findAll());
  }

  @GetMapping("/{name}")
  @Operation(summary = "Get a product by name")
  public ResponseEntity<ProductResponse> getOneProduct(@PathVariable String name) {
    ProductResponse product = productService.getProductByName(name);
    return ResponseEntity.ok().body(product);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Deletes a product by id")
  public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
    productService.deleteProductById(id);
    return ResponseEntity.ok().body("Product deleted sucessfully!");
  }

}
