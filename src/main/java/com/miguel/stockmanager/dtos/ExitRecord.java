package com.miguel.stockmanager.dtos;

import jakarta.validation.constraints.NotNull;

public record ExitRecord(@NotNull Long product_id, @NotNull int quantity) {

}
