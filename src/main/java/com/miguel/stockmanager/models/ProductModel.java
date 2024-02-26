package com.miguel.stockmanager.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "products")
@Table(name = "products")
public class ProductModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(unique = true)
  @NotBlank
  private String name;
  @Column(unique = true)
  @NotBlank
  private String qpm;
  private int quantity = 0;
  private LocalDate createdAt = LocalDate.now();

  @OneToMany(mappedBy = "productModel")
  private List<EntryModel> entries = new ArrayList<>();

  @OneToMany(mappedBy = "productModel")
  private List<ExitModel> exits = new ArrayList<>();

  public ProductModel(Long id, String name, String qpm, int quantity, LocalDate createdAt) {
    this.id = id;
    this.name = name;
    this.qpm = qpm;
    this.quantity = quantity;
    this.createdAt = createdAt;
  }

  public ProductModel() {
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

  public List<EntryModel> getEntries() {
    return entries;
  }

  public List<ExitModel> getExits() {
    return exits;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductModel other = (ProductModel) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
