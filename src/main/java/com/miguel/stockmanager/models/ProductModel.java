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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity(name = "products")
@Table(name = "products")
public class ProductModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(unique = true)
  private String name;
  private int quantity = 0;
  private LocalDate createdAt = LocalDate.now();

  @OneToMany(mappedBy = "productModel")
  private List<EntryModel> entries = new ArrayList<>();

  @OneToMany(mappedBy = "productModel")
  private List<ExitModel> exits = new ArrayList<>();
}
