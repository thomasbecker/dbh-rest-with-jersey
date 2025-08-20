package com.dbh.training.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Product entity representing an item in the inventory.
 * 
 * This model demonstrates:
 * - BigDecimal for precise monetary values
 * - Validation for business rules
 * - SKU (Stock Keeping Unit) patterns
 * - Inventory management fields
 */
public class Product {
    
    private Long id;
    
    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 200, message = "Product name must be between 2 and 200 characters")
    private String name;
    
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    @DecimalMax(value = "999999.99", message = "Price cannot exceed 999999.99")
    @Digits(integer = 6, fraction = 2, message = "Price format is invalid")
    private BigDecimal price;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock cannot be negative")
    @Max(value = 999999, message = "Stock cannot exceed 999999")
    private Integer stock;
    
    @NotBlank(message = "SKU is required")
    @Pattern(regexp = "^[A-Z0-9]{3,20}$", 
             message = "SKU must be 3-20 characters, uppercase letters and numbers only")
    private String sku;
    
    private String imageUrl;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean available;
    
    @PastOrPresent(message = "Creation date cannot be in the future")
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    // Product categories as constants (could be enum)
    public static class Categories {
        public static final String ELECTRONICS = "Electronics";
        public static final String CLOTHING = "Clothing";
        public static final String BOOKS = "Books";
        public static final String HOME = "Home & Garden";
        public static final String SPORTS = "Sports & Outdoors";
        public static final String TOYS = "Toys & Games";
        public static final String FOOD = "Food & Beverages";
        public static final String HEALTH = "Health & Beauty";
        public static final String OTHER = "Other";
    }
    
    // Constructors
    public Product() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public Product(String name, String description, BigDecimal price, 
                   String category, Integer stock, String sku) {
        this();
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.sku = sku;
        updateAvailability();
    }
    
    // Business logic methods
    public void updateAvailability() {
        this.available = stock != null && stock > 0;
    }
    
    public void decreaseStock(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (stock < quantity) {
            throw new IllegalStateException("Insufficient stock");
        }
        this.stock -= quantity;
        updateAvailability();
        updateTimestamp();
    }
    
    public void increaseStock(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.stock += quantity;
        updateAvailability();
        updateTimestamp();
    }
    
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public Integer getStock() {
        return stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
        updateAvailability();
    }
    
    public String getSku() {
        return sku;
    }
    
    public void setSku(String sku) {
        this.sku = sku;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
               Objects.equals(sku, product.sku);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, sku);
    }
    
    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", category='" + category + '\'' +
               ", stock=" + stock +
               ", sku='" + sku + '\'' +
               ", available=" + available +
               '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
        private String category;
        private Integer stock;
        private String sku;
        private String imageUrl;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        
        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }
        
        public Builder price(double price) {
            this.price = BigDecimal.valueOf(price);
            return this;
        }
        
        public Builder category(String category) {
            this.category = category;
            return this;
        }
        
        public Builder stock(Integer stock) {
            this.stock = stock;
            return this;
        }
        
        public Builder sku(String sku) {
            this.sku = sku;
            return this;
        }
        
        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        
        public Product build() {
            Product product = new Product();
            product.id = this.id;
            product.name = this.name;
            product.description = this.description;
            product.price = this.price;
            product.category = this.category;
            product.stock = this.stock;
            product.sku = this.sku;
            product.imageUrl = this.imageUrl;
            product.updateAvailability();
            return product;
        }
    }
}