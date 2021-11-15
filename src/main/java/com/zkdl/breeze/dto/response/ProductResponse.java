package com.zkdl.breeze.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zkdl.breeze.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;

    private String name;

    private Integer price;

    @JsonProperty("category")
    private Category categoryId;

    @JsonProperty("desc")
    private String desc;

    private String image;

    private Integer stock;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}