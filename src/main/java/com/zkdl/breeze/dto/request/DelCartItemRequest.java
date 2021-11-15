package com.zkdl.breeze.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DelCartItemRequest {
    @JsonProperty("product_id")
    @NotNull(message = "Product id cant be null")
    private Long productId;
}