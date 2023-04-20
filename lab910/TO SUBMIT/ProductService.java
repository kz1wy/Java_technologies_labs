package com.lab910.service;

import com.lab910.model.Product;
import com.lab910.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return (Product) productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return (Product) productRepository.save(product);
    }

    public Product updateProduct(int id, Product updatedProduct) {
        Product product = (Product) productRepository.findById(id).orElse(null);

        if (product != null) {
            product.setCode(updatedProduct.getCode());
            product.setProductName(updatedProduct.getProductName());
            product.setPrice(updatedProduct.getPrice());
            product.setIllustration(updatedProduct.getIllustration());
            product.setDescription(updatedProduct.getDescription());

            return (Product) productRepository.save(product);
        }

        return null;
    }

    public Product updateProductPartial(int id, Map<String, Object> updates) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            // Update the fields of the product based on the values in the updates map
            updates.forEach((key, value) -> {
                switch (key) {
                    case "code":
                        product.setCode((String) value);
                        break;
                    case "productName":
                        product.setProductName((String) value);
                        break;
                    case "price":
                        product.setPrice((BigDecimal) value);
                        break;
                    case "illustration":
                        product.setIllustration((String) value);
                        break;
                    case "description":
                        product.setDescription((String) value);
                        break;
                    default:
                        break;
                }
            });
            // Save the updated product to the database
            return productRepository.save(product);
        } else {
            throw new EntityNotFoundException("Product not found with id " + id);
        }
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
