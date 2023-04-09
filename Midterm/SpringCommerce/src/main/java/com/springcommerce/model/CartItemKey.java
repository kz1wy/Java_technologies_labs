package com.springcommerce.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class CartItemKey implements Serializable {
    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "product_id")
    private int productId;

    // getters and setters

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}