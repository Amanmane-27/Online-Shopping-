package com.example.demo.service.IMP;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartRepo;
import com.example.demo.repository.OrderItemRepo;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.OrderService;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public void placeOrder(Long userId) {

        List<Cart> cartItems = cartRepo.findByUserId(userId);

        Order order = new Order();
        order.setUserId(userId);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        double total = 0;
        
        order = orderRepo.save(order);

        for (Cart c : cartItems) {

            Product p = productRepo.findById(c.getProductId()).orElse(null);

            OrderItem item = new OrderItem();
            item.setOrderId(order.getId());
            item.setProductId(p.getProdId());
            item.setQuantity(c.getQuantity());
            item.setPrice(p.getProdPrice());

            total += p.getProdPrice() * c.getQuantity();

            orderItemRepo.save(item);

            p.setProdQuantity(p.getProdQuantity() - c.getQuantity());
            productRepo.save(p);
        }

        order.setTotalAmount(total);
        orderRepo.save(order);

        // clear cart
        cartRepo.deleteAll(cartItems);
    }

    @Override
    public List<Order> getOrders(Long userId) {
        return orderRepo.findByUserId(userId);
    }

	@Override
	public List<Order> getorder(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
