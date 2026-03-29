package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

}
