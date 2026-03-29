package com.example.demo.service.IMP;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepo;
import com.example.demo.service.CartService;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Override
    public void addToCart(Long userId, Long productId) {

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        cart.setQuantity(1);

        cartRepo.save(cart);
    }

    @Override
    public List<Cart> getCartItems(Long userId) {
        return cartRepo.findByUserId(userId);
    }

    @Override
    public void removeFromCart(Long cartId) {
        cartRepo.deleteById(cartId);
    }
}