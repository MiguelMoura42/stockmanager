package com.miguel.stockmanager.exceptions;

public class NameNotFoundException extends RuntimeException {

  public NameNotFoundException(Object name) {
    super("Product not found. Name " + name);
  }
}
