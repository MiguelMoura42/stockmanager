package com.miguel.stockmanager.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDto(@NotBlank String name, @NotNull int quantity) {

}
