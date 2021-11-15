package com.zkdl.breeze.dto.request;

import com.zkdl.breeze.model.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductRequest {
    @NotBlank(message = "Name cant be null")
    @Size(max = 30)
    private String name;

    @NotNull(message = "Price cant be null")
    private Integer price;

    @Size(max = 120)
    private Category category;

    private String desc;

    private String image;

    @NotNull(message = "Stock cant be null")
    private Integer stock;

}