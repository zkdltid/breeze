package com.zkdl.breeze.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CartItemRequest {
    @JsonProperty("product_id")
    @NotNull(message = "Product id cant be null")
    private Long productId;

    @JsonProperty("counts")
    @Min(1)
    @NotNull(message = "Counts cant be null")
    private Integer quantity;
}