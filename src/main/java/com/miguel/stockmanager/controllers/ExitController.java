package com.miguel.stockmanager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguel.stockmanager.requests.ExitRequest;
import com.miguel.stockmanager.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products/exits")
public class ExitController {

  final ProductService productService;

  public ExitController(ProductService productService) {
    this.productService = productService;
  }

  @PutMapping("/{productId}")
  public ResponseEntity<String> exitQuantity(@RequestBody @Valid ExitRequest exitRequest,
      @PathVariable(value = "productId") Long productId) {
    try {
      productService.removeQuantityToProduct(exitRequest, productId);
      return ResponseEntity.ok().body("Quantity removed successfully!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to remove quantity!");
    }
  }

}
