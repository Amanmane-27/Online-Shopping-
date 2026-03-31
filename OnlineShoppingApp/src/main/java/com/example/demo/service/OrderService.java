package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Order;

public interface OrderService {

	void placeOrder(Long userId);

	List<Order> getOrdersByUser(Long userId);
}
