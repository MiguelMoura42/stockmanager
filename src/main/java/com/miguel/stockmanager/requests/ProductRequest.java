package com.miguel.stockmanager.requests;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ProductRequest {

  private String name;

  @JsonSetter("name")
  public void formatNameToUpperCase(String name) {
    this.name = name.toUpperCase();
  }

  public ProductRequest(String name) {
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
