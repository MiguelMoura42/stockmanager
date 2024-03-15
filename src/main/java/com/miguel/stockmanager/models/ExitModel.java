package com.miguel.stockmanager.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "exits")
public class ExitModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "product_id")
  private ProductModel productModel;
  private LocalDate exitDate = LocalDate.now();
  private int quantity;

  public ExitModel() {
  }

  public ExitModel(Long id, ProductModel productModel, LocalDate exitDate, int quantity) {
    this.id = id;
    this.productModel = productModel;
    this.exitDate = exitDate;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProductModel getProductModel() {
    return productModel;
  }

  public void setProductModel(ProductModel productModel) {
    this.productModel = productModel;
  }

  public LocalDate getExitDate() {
    return exitDate;
  }

  public void setExitDate(LocalDate exitDate) {
    this.exitDate = exitDate;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
