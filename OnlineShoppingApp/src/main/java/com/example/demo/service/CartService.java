package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Cart;

public interface CartService {

    void addToCart(Long userId, Long productId);

    List<Cart> getCartItems(Long userId);

    void removeFromCart(Long cartId);
}
