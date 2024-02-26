package com.miguel.stockmanager.requests;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ProductRequest {

  private String name;
  private String qpm;

  @JsonSetter("name")
  public void formatNameToUpperCase(String name) {
    this.name = name.toUpperCase();
  }

  @JsonSetter("qpm")
  public void formatQpmToUpperCase(String qpm) {
    this.qpm = qpm.toUpperCase();
  }

  public ProductRequest(String name, String qpm) {
    this.name = name;
    this.qpm = qpm;
  }

  public ProductRequest() {
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

}
