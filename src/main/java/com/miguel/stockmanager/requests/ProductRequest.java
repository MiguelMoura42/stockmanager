package com.miguel.stockmanager.requests;

import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.validation.constraints.NotBlank;

public class ProductRequest {

  @NotBlank
  private String name;

  @JsonSetter("name")
  public void formatNameToUpperCase(String name) {
    this.name = name.toUpperCase();
  }

  public ProductRequest(@NotBlank String name) {
    this.name = name;
  }

  public ProductRequest() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
