package com.lab910.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;

    @Column(name = "OrderNumber", unique = true, nullable = false)
    private String orderNumber;

    @Column(name = "TotalSellingPrice", nullable = false)
    private BigDecimal totalSellingPrice;

    @Column(name = "ProductList")
    private String productList;

    public Order() {}

    public Order(String orderNumber, BigDecimal totalSellingPrice, String productList) {
        this.orderNumber = orderNumber;
        this.totalSellingPrice = totalSellingPrice;
        this.productList = productList;
    }

    // Getters and setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getTotalSellingPrice() {
        return totalSellingPrice;
    }

    public void setTotalSellingPrice(BigDecimal totalSellingPrice) {
        this.totalSellingPrice = totalSellingPrice;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }
}
