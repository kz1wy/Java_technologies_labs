package com.lab910.service;

import com.lab910.model.Order;
import com.lab910.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(int id, Order updatedOrder) {
        Order order = orderRepository.findById(id).orElse(null);

        if (order != null) {
            order.setOrderNumber(updatedOrder.getOrderNumber());
            order.setTotalSellingPrice(updatedOrder.getTotalSellingPrice());
            order.setProductList(updatedOrder.getProductList());

            return orderRepository.save(order);
        }

        return null;
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
