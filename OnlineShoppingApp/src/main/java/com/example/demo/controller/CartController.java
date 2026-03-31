package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;

@Controller
public class CartController {

    @Autowired
    private CartService service;

    @Autowired
    private ProductService productService;

    @GetMapping("/addToCart/{userId}/{productId}")
    public String addToCart(@PathVariable Long userId,
                           @PathVariable Long productId) {
        service.addToCart(userId, productId);
        return "redirect:/main/" + userId;
    }

    @GetMapping("/cart/{userId}")
    public String viewCart(@PathVariable Long userId, Model model) {

        List<Cart> cartItems = service.getCartItems(userId);

        List<Product> cartProducts = new ArrayList<>();
        double totalAmount = 0;

        for (Cart c : cartItems) {
            Product p = productService.getProductById(c.getProductId());
            if (p != null) {
                cartProducts.add(p);
                totalAmount += p.getProdPrice() * c.getQuantity();
            } else {
                Product placeholder = new Product();
                placeholder.setProdId(c.getProductId());
                placeholder.setProdName("Unknown Product");
                placeholder.setProdPrice(0);
                cartProducts.add(placeholder);
            }
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("userId", userId);

        return "cart";
    }

    @GetMapping("/removeCart/{userId}/{id}")
    public String remove(@PathVariable Long userId,
                         @PathVariable Long id) {
        service.removeFromCart(id);
        return "redirect:/cart/" + userId;
    }
}