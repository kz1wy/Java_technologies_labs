package com.springcommerce.service;

import com.springcommerce.model.Cart;
import com.springcommerce.model.CartItem;
import com.springcommerce.model.Product;
import com.springcommerce.repository.CartItemRepository;
import com.springcommerce.repository.CartRepository;
import com.springcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.util.*;

@Service
@Transactional
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart addProduct(int cartId, int productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Optional<CartItem> existingCartItem = cart.getCartItems()
                .stream()
                .filter(ci -> ci.getProduct().getId() == product.getId())
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem item = existingCartItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cart.getCartItems().add(cartItem);
        }

        return cartRepository.save(cart);
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }


}
