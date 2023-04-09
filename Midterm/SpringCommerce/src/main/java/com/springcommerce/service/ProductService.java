package com.springcommerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import com.springcommerce.repository.ProductRepository;
import com.springcommerce.model.Product;
import com.springcommerce.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Product updateProduct(int id, Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setColor(product.getColor());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductsByCriteria(String category, Double minPrice, Double maxPrice, String brand, String color) {
        List<Product> productList = getAllProducts();
        if (category == null && minPrice == null && maxPrice == null && brand == null && color == null) {
            return productList;
        }
        if (category != null && !category.isEmpty()) {
            productList = productList.stream()
                    .filter(product -> product.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }
        if (minPrice != null) {
            productList = productList.stream()
                    .filter(product -> product.getPrice().doubleValue() >= minPrice)
                    .collect(Collectors.toList());
        }
        if (maxPrice != null) {
            productList = productList.stream()
                    .filter(product -> product.getPrice().doubleValue() <= maxPrice)
                    .collect(Collectors.toList());
        }
        if (brand != null && !brand.isEmpty()) {
            productList = productList.stream()
                    .filter(product -> product.getBrand().equalsIgnoreCase(brand))
                    .collect(Collectors.toList());
        }
        if (color != null && !color.isEmpty()) {
            productList = productList.stream()
                    .filter(product -> product.getColor().equalsIgnoreCase(color))
                    .collect(Collectors.toList());
        }

        return productList;
    }


}
