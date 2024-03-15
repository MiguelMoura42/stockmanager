package com.miguel.stockmanager.exceptions;

public class QuantityAboveLimitException extends RuntimeException {
  public QuantityAboveLimitException() {
    super("Check the quantity in stock before making a withdrawal.");
  }
}
