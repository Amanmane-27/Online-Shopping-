package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    // Show Add Product Page
    @GetMapping("/addProduct/{userId}")
    public String showProduct(@PathVariable Long userId, Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("userId", userId);
        return "product/add";
    }

    // Save Product (new)
    @PostMapping("/saveProduct/{userId}")
    public String save(@PathVariable Long userId, Product product) {
        service.saveProduct(product);
        return "redirect:/products/" + userId;
    }

    // Show All Products
    @GetMapping("/products/{userId}")
    public String getAll(@PathVariable Long userId, Model model) {
        model.addAttribute("products", service.getAllProducts());
        model.addAttribute("userId", userId);
        return "product/list";
    }

    // Show Edit Product Page
    @GetMapping("/editProduct/{userId}/{id}")
    public String editProduct(@PathVariable Long userId,
                              @PathVariable Long id, Model model) {

        Product product = service.getProductById(id);
        if (product == null) {
            return "redirect:/products/" + userId;
        }
        model.addAttribute("product", product);
        model.addAttribute("userId", userId);
        return "product/edit";
    }

    // Update Product
    @PostMapping("/updateProduct/{userId}")
    public String updateProduct(@PathVariable Long userId, Product product) {
        service.saveProduct(product);
        return "redirect:/products/" + userId;
    }

    // Delete Product
    @GetMapping("/deleteProduct/{userId}/{id}")
    public String delete(@PathVariable Long userId,
                         @PathVariable Long id) {
        service.deleteProduct(id);
        return "redirect:/products/" + userId;
    }

    // Main page - show products from database
    @GetMapping("/main/{userId}")
    public String mainPage(@PathVariable Long userId, Model model) {
        model.addAttribute("products", service.getAllProducts());
        model.addAttribute("userId", userId);
        return "main";
    }
}