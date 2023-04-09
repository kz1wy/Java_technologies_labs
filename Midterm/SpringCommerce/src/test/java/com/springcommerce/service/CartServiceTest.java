package com.springcommerce.service;

import com.springcommerce.model.Cart;
import com.springcommerce.repository.CartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    public void addProduct_CallsRepositoryWithCorrectArguments() {
        int cartId = 1;
        int productId = 2;
        int quantity = 3;
        Cart cart = new Cart();

        Mockito.when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        cartService.addProduct(cartId, productId, quantity);

        Mockito.verify(cartRepository, Mockito.times(1)).findById(cartId);
        Mockito.verify(cartRepository, Mockito.times(1)).save(cart);
    }
}