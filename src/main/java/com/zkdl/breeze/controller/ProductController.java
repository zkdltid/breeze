package com.zkdl.breeze.controller;

import com.zkdl.breeze.dto.request.DelProductRequest;
import com.zkdl.breeze.dto.request.GetProductRequest;
import com.zkdl.breeze.dto.request.PatchProductRequest;
import com.zkdl.breeze.dto.request.ProductRequest;
import com.zkdl.breeze.dto.response.ProductResponse;
import com.zkdl.breeze.model.Product;
import com.zkdl.breeze.repository.ProductRepository;
import com.zkdl.breeze.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductServices productServices;

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok(productServices.listCategory());
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProduct(
            @RequestParam(value = "name",required = false ) String name,
            @RequestParam(value = "price",required = false ) Integer price,
            @RequestParam(value = "category_id",required = false ) Long categoryId,
            @RequestParam(value = "stock",required = false ) Integer stock
            ) {
        GetProductRequest getProductRequest = new GetProductRequest(name,price,categoryId,stock);
        System.out.println("test");
        return ResponseEntity.ok(productServices.listProduct(getProductRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductDetail(@PathVariable("id") Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productServices.addProduct(productRequest);
        return new ResponseEntity<>(productResponse,HttpStatus.CREATED);
    }
    @PatchMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editProduct(@Valid @RequestBody PatchProductRequest patchProductRequest) {
        ProductResponse productResponse = productServices.editProduct(patchProductRequest);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }
    @DeleteMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delProduct(@Valid @RequestBody DelProductRequest delProductRequest) {
        ProductResponse productResponse = productServices.delProduct(delProductRequest);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }
}