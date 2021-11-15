package com.zkdl.breeze.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductRequest {
    private String name;

    private Integer price;

    private Long categoryId;

    private Integer stock;

}