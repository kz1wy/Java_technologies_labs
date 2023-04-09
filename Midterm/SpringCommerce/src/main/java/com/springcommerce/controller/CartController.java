package com.springcommerce.controller;

import com.springcommerce.model.Cart;
import com.springcommerce.model.CartItem;
import com.springcommerce.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springcommerce.service.CartService;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;


    @PostMapping
    @ResponseBody
    public ResponseEntity<String> addProductToCart(@RequestParam(name = "cartId") int cartId,
                                                   @RequestParam(name = "productId") int productId,
                                                   @RequestParam(name = "quantity") int quantity) {
        try {
            cartService.addProduct(cartId, productId, quantity);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/cartItems")
    public ModelAndView getAllCartItems() {
        List<CartItem> cartItems = cartService.getAllCartItems();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cartItems", cartItems);
        modelAndView.setViewName("cart-item-list");
        return modelAndView;
    }


}
