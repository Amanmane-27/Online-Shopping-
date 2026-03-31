package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.service.OrderService;

@Controller
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/placeOrder/{userId}")
    public String placeOrder(@PathVariable Long userId) {
        service.placeOrder(userId);
        return "redirect:/orders/" + userId;
    }

    @GetMapping("/orders/{userId}")
    public String viewOrders(@PathVariable Long userId, Model model) {
        model.addAttribute("orders", service.getOrdersByUser(userId));
        model.addAttribute("userId", userId);
        return "order/list";
    }
}