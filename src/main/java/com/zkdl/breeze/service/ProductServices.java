package com.zkdl.breeze.service;

import com.zkdl.breeze.dto.request.DelProductRequest;
import com.zkdl.breeze.dto.request.GetProductRequest;
import com.zkdl.breeze.dto.request.PatchProductRequest;
import com.zkdl.breeze.dto.request.ProductRequest;
import com.zkdl.breeze.dto.response.ProductResponse;
import com.zkdl.breeze.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductServices {
    List<Category> listCategory();
    List<ProductResponse> listProduct(GetProductRequest productRequest);
    ProductResponse addProduct(ProductRequest productRequest);
    ProductResponse editProduct(PatchProductRequest patchProductRequest);
    ProductResponse delProduct(DelProductRequest delProductRequest);
}


/**
 * cart_list:[
 * cart_response,
 * ]
 */