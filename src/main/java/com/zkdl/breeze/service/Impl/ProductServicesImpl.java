package com.zkdl.breeze.service.Impl;

import com.zkdl.breeze.dto.request.DelProductRequest;
import com.zkdl.breeze.dto.request.GetProductRequest;
import com.zkdl.breeze.dto.request.PatchProductRequest;
import com.zkdl.breeze.dto.request.ProductRequest;
import com.zkdl.breeze.dto.response.ProductResponse;
import com.zkdl.breeze.exception.ProductIsNotExistInCartException;
import com.zkdl.breeze.model.Category;
import com.zkdl.breeze.model.Product;
import com.zkdl.breeze.repository.CategoryRepository;
import com.zkdl.breeze.repository.ProductRepository;
import com.zkdl.breeze.service.ProductServices;
import com.zkdl.breeze.util.PropertyManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServicesImpl implements ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;



    public List<Category> listCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return  categoryList;
    }

    public List<ProductResponse> listProduct(GetProductRequest productRequest) {
        List<ProductResponse> products = new ArrayList<>();
        Product findProduct = new Product();
        System.out.println(productRequest);
        BeanUtils.copyProperties(productRequest, findProduct);
        //if filter by category
        if(productRequest.getCategoryId()!=null){
            Category category = categoryRepository.findById(productRequest.getCategoryId()).get();
            findProduct.setCategoryId(category);
        }

        System.out.println("xxx"+findProduct);
        productRepository.findAll(Example.of(findProduct)).forEach(product -> {
            ProductResponse setToResponse = new ProductResponse();
            BeanUtils.copyProperties(product, setToResponse);
            products.add(setToResponse);
        });

        return products;
    }


    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        Product productResult = productRepository.save(product);
        // convert to response
        ProductResponse addResponse = new ProductResponse();
        BeanUtils.copyProperties(productResult, addResponse);
        return addResponse;
    }

    public ProductResponse editProduct(PatchProductRequest patchProductRequest) {
        try {
            Optional<Product> findProduct = productRepository.findById(patchProductRequest.getId());
            Product editProduct = findProduct.get();
            PropertyManager.copyNonNullProperties(patchProductRequest, editProduct);
            Product editProductResult = productRepository.save(editProduct);
            ProductResponse addResponse = new ProductResponse();
            PropertyManager.copyNonNullProperties(editProductResult, addResponse);
            return addResponse;
        } catch (NoSuchElementException e) {
            throw new ProductIsNotExistInCartException("Product Is Not Exist");
        }
    }

    public ProductResponse delProduct(DelProductRequest delPatchProductRequest) {
        try {
            Optional<Product> findProduct = productRepository.findById(delPatchProductRequest.getProductId());
            productRepository.deleteById(findProduct.get().getId());
            ProductResponse addResponse = new ProductResponse();
            PropertyManager.copyNonNullProperties(findProduct.get(), addResponse);
            return addResponse;
        } catch (NoSuchElementException e) {
            throw new ProductIsNotExistInCartException("Product Is Not Exist In Cart");
        }
    }

}
