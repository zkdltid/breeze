package com.zkdl.breeze.dto.response.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListResponse {
    @JsonProperty("size")
    private int size;

    @JsonProperty("data_list")
    private List<?> data;

    public int getSize() {
        return this.data.size();
    }
}