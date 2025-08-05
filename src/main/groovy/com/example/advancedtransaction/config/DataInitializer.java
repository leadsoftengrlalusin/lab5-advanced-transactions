package com.example.advancedtransaction.config;

import com.example.advancedtransaction.entity.Product;
import com.example.advancedtransaction.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            Product product1 = new Product("LAPTOP-001", "Gaming Laptop", new BigDecimal("1299.99"), 10);
            Product product2 = new Product("PHONE-001", "Smartphone", new BigDecimal("699.99"), 25);
            Product product3 = new Product("TABLET-001", "Tablet", new BigDecimal("399.99"), 15);
            Product product4 = new Product("HEADPHONES-001", "Wireless Headphones", new BigDecimal("199.99"), 30);

            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);

            System.out.println("Test products created successfully!");
        }
    }
}