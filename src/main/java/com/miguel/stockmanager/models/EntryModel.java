package com.miguel.stockmanager.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;

@Entity(name = "entries")
public class EntryModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "product_id")
  private ProductModel productModel;
  private LocalDate entryDate = LocalDate.now();
  @Positive
  private int quantity;

  public EntryModel() {
  }

  public EntryModel(Long id, ProductModel productModel, LocalDate entryDate, int quantity) {
    this.id = id;
    this.productModel = productModel;
    this.entryDate = entryDate;
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

  public LocalDate getentryDate() {
    return entryDate;
  }

  public void setentryDate(LocalDate entryDate) {
    this.entryDate = entryDate;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
