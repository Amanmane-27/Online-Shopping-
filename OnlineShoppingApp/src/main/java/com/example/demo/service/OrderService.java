package com.example.demo.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;

@Service
public interface OrderService {

	void placeOrder(Long userId);
	
	List<Order> getorder(Long id);

//	@Nullable
	Object getOrders(Long userId);
	
}
