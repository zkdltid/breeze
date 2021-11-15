package com.zkdl.breeze.service.Impl;

import com.zkdl.breeze.dto.response.cartItem.CartItemResponse;
import com.zkdl.breeze.exception.ProductIsExistInCartException;
import com.zkdl.breeze.exception.ProductIsNotExistInCartException;
import com.zkdl.breeze.model.CartItem;
import com.zkdl.breeze.model.Product;
import com.zkdl.breeze.model.User;
import com.zkdl.breeze.repository.CartItemRepository;
import com.zkdl.breeze.service.CartItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartItemServicesImpl implements CartItemServices {
    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItemResponse> listCartItems(User user) {
        List<CartItem> findResult = cartItemRepository.findByUser(user);
        List<CartItemResponse> cartItemResponseList = new ArrayList<>();
        findResult.forEach(cartItem -> cartItemResponseList.add(
                new CartItemResponse(
                        cartItem.getId(),
                        cartItem.getProduct(),
                        cartItem.getQuantity()
                )
        ));
        return cartItemResponseList;
    }

    public CartItemResponse addCartItem(User user, Product product, Integer quantity) {
        Boolean existInCarts = cartItemRepository.existsByUserAndProduct(user, product);
        if (existInCarts) {
            throw new ProductIsExistInCartException("Product Is Exist In Cart");
        } else {
            CartItem userCartItem = new CartItem();
            userCartItem.setUser(user);
            userCartItem.setProduct(product);
            userCartItem.setQuantity(quantity);
            CartItem cartItemResult = cartItemRepository.save(userCartItem);
            // convert to response
            CartItemResponse addResponse = new CartItemResponse();
            addResponse.setId(cartItemResult.getId());
            addResponse.setProduct(cartItemResult.getProduct());
            addResponse.setQuantity(cartItemResult.getQuantity());
            return addResponse;
        }
    }

    public CartItemResponse editCartItem(User user, Product product, Integer quantity) {
        try {
            Optional<CartItem> existInCarts = cartItemRepository.findByUserAndProduct(user, product);
            CartItem editCartItem = new CartItem();
            editCartItem.setId(existInCarts.get().getId());
            editCartItem.setUser(user);
            editCartItem.setProduct(product);
            editCartItem.setQuantity(quantity);
            CartItem cartItemResult = cartItemRepository.save(editCartItem);

            // convert to response
            CartItemResponse addResponse = new CartItemResponse();
            addResponse.setId(cartItemResult.getId());
            addResponse.setProduct(cartItemResult.getProduct());
            addResponse.setQuantity(cartItemResult.getQuantity());
            return addResponse;
        } catch (NoSuchElementException e) {
            throw new ProductIsNotExistInCartException("Product Is Not Exist In Cart");
        }
    }

    public CartItemResponse deleteCartItem(User user, Product product) {
        try {
            Optional<CartItem> existInCarts = cartItemRepository.findByUserAndProduct(user, product);
            cartItemRepository.deleteById(existInCarts.get().getId());
            // convert to response
            CartItemResponse addResponse = new CartItemResponse();
            addResponse.setId(existInCarts.get().getId());
            addResponse.setProduct(product);
            addResponse.setQuantity(0);
            return addResponse;
        } catch (NoSuchElementException e) {
            throw new ProductIsNotExistInCartException("Product Is Not Exist In Cart");
        }
    }

}
