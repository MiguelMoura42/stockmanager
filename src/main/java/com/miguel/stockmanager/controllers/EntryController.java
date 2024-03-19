package com.miguel.stockmanager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguel.stockmanager.requests.EntryRequest;
import com.miguel.stockmanager.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products/entries")
@Tag(name = "Entries", description = "Controller to entries")
public class EntryController {

  final ProductService productService;

  public EntryController(ProductService productService) {
    this.productService = productService;
  }

  @PutMapping("/{productId}")
  @Operation(summary = "Adds quantity to a product")
  public ResponseEntity<String> addQuantity(@RequestBody @Valid EntryRequest entryRequest,
      @PathVariable Long productId) {
    productService.addQuantityToProduct(entryRequest, productId);
    return ResponseEntity.ok().body("Quantity added successfully!");
  }
}
