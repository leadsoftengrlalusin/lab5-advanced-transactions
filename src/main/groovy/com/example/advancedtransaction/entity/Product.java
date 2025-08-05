package com.example.advancedtransaction.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String sku;

    @NotBlank
    private String name;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @NotNull
    private Integer stockQuantity;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    public enum ProductStatus {
        ACTIVE, INACTIVE, OUT_OF_STOCK
    }

    // Constructors, getters, and setters
    public Product() {}

    public Product(String sku, String name, BigDecimal price, Integer stockQuantity) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.status = ProductStatus.ACTIVE;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public ProductStatus getStatus() { return status; }
    public void setStatus(ProductStatus status) { this.status = status; }
}