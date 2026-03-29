package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Product;
import com.example.demo.service.IMP.ProductServiceImp;


@Controller
public class ProductController {

    @Autowired
    private ProductServiceImp service;

    // Show Add Product Page
    @GetMapping("/addProduct/{userId}")
    public String showProduct(@PathVariable Long userId, Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("userId", userId);
        return "product/add";
    }
//    @GetMapping("/addProduct/")
//    public String showAllProduct(@PathVariable Long userId, Model model) {
//    	model.addAttribute("product", new Product());
//    	model.addAttribute("userId", userId); 
//    	return "product/add";
//    }

    // Save Product
    @PostMapping("/saveProduct/{userId}")
    public String save(@PathVariable Long userId, Product product) {
        service.saveProduct(product);
        return "redirect:/products/" + userId;
    }

    // Show Products
    @GetMapping("/products/{userId}")
    public String getAll(@PathVariable Long userId, Model model) {

        model.addAttribute("products", service.getAllProducts());
        model.addAttribute("userId", userId); 

        return "product/list";
    }

    // Delete Product
    @GetMapping("/deleteProduct/{userId}/{id}")
    public String delete(@PathVariable Long userId,
                         @PathVariable Long id) {

        service.deleteProduct(id);

        return "redirect:/products/" + userId;
    }
}