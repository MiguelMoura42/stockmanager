package com.miguel.stockmanager.responses;

import com.miguel.stockmanager.models.ProductModel;

public class ProductResponse {
  private Long id;
  private String name;
  private int quantity;

  public ProductResponse(ProductModel productModel) {
    this.id = productModel.getId();
    this.name = productModel.getName();
    this.quantity = productModel.getQuantity();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
