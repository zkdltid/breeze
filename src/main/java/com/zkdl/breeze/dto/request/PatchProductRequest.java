package com.zkdl.breeze.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PatchProductRequest {
    @NotNull(message = "Id can not be null")
    private Long id;

    @Size(max = 30)
    private String name;

    private Integer price;

    @Size(max = 120)
    private String category;

    private String desc;

    private String image;

    @Min(1)
    private Integer stock;

}