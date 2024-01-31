package com.miguel.stockmanager.requests;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.miguel.stockmanager.models.ProductModel;

import jakarta.validation.constraints.NotBlank;

public class ProductRequest {

  @NotBlank
  private String name;

  @JsonSetter("name")
  public void formatNameToUpperCase(String name) {
    this.name = name.toUpperCase();
  }

  public ProductModel fromProductRequest() {
    return new ProductModel(null, name, 0, null);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductRequest(@NotBlank String name) {
    this.name = name;
  }

  public ProductRequest() {
  }

}
