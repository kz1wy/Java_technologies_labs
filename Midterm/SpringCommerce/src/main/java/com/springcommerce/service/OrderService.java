package com.springcommerce.service;

import com.springcommerce.model.Order;
import com.springcommerce.repository.OrderRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findById(int id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void deleteById(int id) {
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Order not found with id: " + id);
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Order order) {
        Optional<Order> optionalOrder = orderRepository.findById(order.getId());
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            existingOrder.setCustomerName(order.getCustomerName());
            existingOrder.setCustomerAddress(order.getCustomerAddress());
            existingOrder.setCreatedAt(order.getCreatedAt());
            return orderRepository.save(existingOrder);
        } else {
            throw new EntityNotFoundException("Order not found with id " + order.getId());
        }
    }
}
