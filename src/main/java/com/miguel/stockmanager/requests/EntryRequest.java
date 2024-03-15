package com.miguel.stockmanager.requests;

import jakarta.validation.constraints.Positive;

public class EntryRequest {
  @Positive
  private int quantity;

  public EntryRequest() {
  }

  public EntryRequest(int quantity) {
    this.quantity = quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
