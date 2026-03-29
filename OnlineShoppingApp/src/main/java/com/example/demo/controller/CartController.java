package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.service.CartService;


@Controller
public class CartController {

    @Autowired
    private CartService service;

    // Add to cart
    @GetMapping("/addToCart/{userId}/{productId}")
    public String addToCart(@PathVariable Long userId,
                           @PathVariable Long productId) {

        service.addToCart(userId, productId);

        return "redirect:/products/" + userId;
    }

    // View cart
    @GetMapping("/cart/{userId}")
    public String viewCart(@PathVariable Long userId, Model model) {

        model.addAttribute("cartItems",
                service.getCartItems(userId));

        return "cart";
    }

    // Remove item
    @GetMapping("/removeCart/{userId}/{id}")
    public String remove(@PathVariable Long userId,
                         @PathVariable Long id) {

        service.removeFromCart(id);

        return "redirect:/cart/" + userId;
    }
}