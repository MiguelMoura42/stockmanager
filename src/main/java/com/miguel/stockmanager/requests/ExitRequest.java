package com.miguel.stockmanager.requests;

import jakarta.validation.constraints.Positive;

public class ExitRequest {
  @Positive
  private int quantity;

  public ExitRequest() {
  }

  public ExitRequest(int quantity) {
    this.quantity = quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
