package com.miguel.stockmanager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguel.stockmanager.requests.ExitRequest;
import com.miguel.stockmanager.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products/exits")
@Tag(name = "Exits", description = "Controller to exits")
public class ExitController {

  final ProductService productService;

  public ExitController(ProductService productService) {
    this.productService = productService;
  }

  @PutMapping("/{productId}")
  @Operation(summary = "Removes quantity to a product")
  public ResponseEntity<String> exitQuantity(@RequestBody @Valid ExitRequest exitRequest,
      @PathVariable Long productId) {
    productService.removeQuantityToProduct(exitRequest, productId);
    return ResponseEntity.ok().body("Quantity removed successfully!");
  }
}
