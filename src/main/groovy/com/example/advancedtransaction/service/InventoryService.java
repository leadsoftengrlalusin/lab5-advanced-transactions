package com.example.advancedtransaction.service;

import com.example.advancedtransaction.entity.Product;
import com.example.advancedtransaction.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    private final ProductRepository productRepository;

    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void reserveInventory(String sku, int quantity) {
        Product product = productRepository.findBySkuWithLock(sku)
                .orElseThrow(() -> new RuntimeException("Product not found: " + sku));

        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock for product: " + sku);
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);

        if (product.getStockQuantity() == 0) {
            product.setStatus(Product.ProductStatus.OUT_OF_STOCK);
        }

        productRepository.save(product);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void releaseInventory(String sku, int quantity) {
        Product product = productRepository.findBySkuWithLock(sku)
                .orElseThrow(() -> new RuntimeException("Product not found: " + sku));

        product.setStockQuantity(product.getStockQuantity() + quantity);

        if (product.getStatus() == Product.ProductStatus.OUT_OF_STOCK) {
            product.setStatus(Product.ProductStatus.ACTIVE);
        }

        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProduct(String sku) {
        return productRepository.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Product not found: " + sku));
    }
}