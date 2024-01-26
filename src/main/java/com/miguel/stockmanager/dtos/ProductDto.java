package com.miguel.stockmanager.dtos;

import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "name")
public class ProductDto {

  @NotBlank
  private String name;

  @JsonSetter("name")
  public void formatNameToUpperCase(String name) {
    this.name = name.toUpperCase();
  }

}
