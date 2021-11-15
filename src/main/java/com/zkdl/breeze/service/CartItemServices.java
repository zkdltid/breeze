package com.zkdl.breeze.service;

import com.zkdl.breeze.model.User;
import com.zkdl.breeze.dto.response.cartItem.CartItemResponse;
import com.zkdl.breeze.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartItemServices {
    List<CartItemResponse> listCartItems(User user);
    CartItemResponse addCartItem(User user, Product product, Integer quantity);
    CartItemResponse editCartItem(User user, Product product, Integer quantity);
    CartItemResponse deleteCartItem(User user, Product product);

}


/**
 * cart_list:[
 * cart_response,
 * ]
 */