package com.zkdl.breeze.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zkdl.breeze.model.User;
import com.zkdl.breeze.service.CartItemServices;
import com.zkdl.breeze.dto.request.CartItemRequest;
import com.zkdl.breeze.dto.request.DelCartItemRequest;
import com.zkdl.breeze.dto.response.cartItem.CartItemResponse;
import com.zkdl.breeze.exception.ProductIsNotExistException;
import com.zkdl.breeze.model.Product;
import com.zkdl.breeze.repository.ProductRepository;
import com.zkdl.breeze.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartItemServices cartItemServices;


    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getCartItems(Principal userLogin) throws JsonProcessingException {
        /**
         * 取得user
         * 取得user底下所有的cart item
         * 利用List Response 回傳
         * **/
        String username = userLogin.getName();
        Optional<User> user = userRepository.findByUsername(username);

        List<CartItemResponse> cartItemResponseList = cartItemServices.listCartItems(user.get());
        /**
         * TODO: convert to pagination
         */
//        ListResponse resultResponse = new ListResponse();
//        List cartDataList = new ArrayList();
//        cartItemResponseList.forEach(item -> {
//            cartDataList.add(item);
//        });
//        resultResponse.setData(cartDataList);
        return new ResponseEntity<>(cartItemResponseList, HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> addProduct(@Valid @RequestBody CartItemRequest cartItemRequest, Principal userLogin) throws JsonProcessingException {
        /**
         * 取得user
         * 檢查product是否存在
         * 加入cart items
         * **/
        String username = userLogin.getName();
        try {
            // 檢查product 存在
            Optional<Product> product = productRepository.findById(cartItemRequest.getProductId());
            // 取得user detail
            Optional<User> user = userRepository.findByUsername(username);
            // Add Cart Item Save To DB
            CartItemResponse cartItemResult = cartItemServices.addCartItem(user.get(), product.get(), cartItemRequest.getQuantity());
            return new ResponseEntity<>(cartItemResult, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            logger.error("Add CartItem Error : {} ", e.getMessage());
            throw new ProductIsNotExistException("Product is not exist");
        }
    }


    @PatchMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> editProduct(@Valid @RequestBody CartItemRequest cartItemRequest, Principal userLogin) throws JsonProcessingException {
        /**
         * 取得user
         * 檢查product是否存在
         * 修改cart items
         * **/
        String username = userLogin.getName();
        try {
            // 檢查product 存在
            Optional<Product> product = productRepository.findById(cartItemRequest.getProductId());
            // 取得user detail
            Optional<User> user = userRepository.findByUsername(username);
            // Add Cart Item Save To DB
            CartItemResponse cartItemResult = cartItemServices.editCartItem(user.get(), product.get(), cartItemRequest.getQuantity());
            return new ResponseEntity<>(cartItemResult, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            logger.error("Add CartItem Error : {} ", e.getMessage());
            throw new ProductIsNotExistException("Product is not exist");
        }
    }

    @DeleteMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@Valid @RequestBody DelCartItemRequest delCartItemRequest, Principal userLogin) throws JsonProcessingException {
        /**
         * 取得user
         * 檢查product是否存在
         * 刪除cart items
         * **/
        String username = userLogin.getName();
        try {
            // 檢查product 存在
            Optional<Product> product = productRepository.findById(delCartItemRequest.getProductId());
            // 取得user detail
            Optional<User> user = userRepository.findByUsername(username);
            // Add Cart Item Save To DB
            CartItemResponse cartItemResult = cartItemServices.deleteCartItem(user.get(), product.get());
            return new ResponseEntity<>(cartItemResult, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            logger.error("Add CartItem Error : {} ", e.getMessage());
            throw new ProductIsNotExistException("Product is not exist");
        }
    }
}