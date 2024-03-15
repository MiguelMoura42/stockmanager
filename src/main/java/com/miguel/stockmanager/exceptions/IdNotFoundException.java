package com.miguel.stockmanager.exceptions;

public class IdNotFoundException extends RuntimeException {
  public IdNotFoundException(Long id) {
    super("Product not found. Id " + id);
  }
}
