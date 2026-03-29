package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
	
    List<Order> findByUserId(Long userId);

}
