package com.zkdl.breeze.dto.response.cartItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zkdl.breeze.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    @JsonProperty("cartitem_id")
    private Long id;

    @JsonProperty("product")
    private Product product;

    @JsonProperty("item_counts")
    private int quantity;
}