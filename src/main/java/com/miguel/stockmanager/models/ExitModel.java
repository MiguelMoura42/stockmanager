package com.miguel.stockmanager.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;

@Entity(name = "exits")
public class ExitModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private ProductModel productModel;

  private LocalDate exitDate = LocalDate.now();
  @Positive
  private int quantityRemoved;

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

  public int getQuantityRemoved() {
    return quantityRemoved;
  }

  public void setQuantityRemoved(int quantityRemoved) {
    this.quantityRemoved = quantityRemoved;
  }

  public ExitModel(Long id, ProductModel productModel, LocalDate exitDate, @Positive int quantityRemoved) {
    this.id = id;
    this.productModel = productModel;
    this.exitDate = exitDate;
    this.quantityRemoved = quantityRemoved;
  }

  public ExitModel() {
  }
}
