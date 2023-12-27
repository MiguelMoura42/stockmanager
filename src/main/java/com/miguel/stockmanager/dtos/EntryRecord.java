package com.miguel.stockmanager.dtos;

import jakarta.validation.constraints.NotNull;

public record EntryRecord(@NotNull Long product_id, @NotNull int quantity) {

}
