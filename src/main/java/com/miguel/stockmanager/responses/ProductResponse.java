package com.miguel.stockmanager.responses;

import java.time.LocalDate;

import com.miguel.stockmanager.models.ProductModel;

public class ProductResponse {
  private String name;
  private String qpm;
  private int quantity;
  private LocalDate createdAt;

  public ProductResponse(ProductModel productModel) {
    this.name = productModel.getName();
    this.qpm = productModel.getQpm();
    this.quantity = productModel.getQuantity();
    this.createdAt = productModel.getCreatedAt();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getQpm() {
    return qpm;
  }

  public void setQpm(String qpm) {
    this.qpm = qpm;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

}
