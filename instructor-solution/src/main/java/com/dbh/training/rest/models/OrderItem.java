package com.dbh.training.rest.models;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * OrderItem represents a line item in an order.
 * 
 * This is an embedded object that captures:
 * - Product reference
 * - Quantity ordered
 * - Price at the time of order (snapshot)
 */
public class OrderItem {
    
    @NotNull(message = "Product ID is required")
    private Long productId;
    
    @NotBlank(message = "Product name is required")
    private String productName;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 999, message = "Quantity cannot exceed 999")
    private Integer quantity;
    
    @NotNull(message = "Unit price is required")
    @DecimalMin(value = "0.01", message = "Unit price must be at least 0.01")
    @Digits(integer = 6, fraction = 2, message = "Price format is invalid")
    private BigDecimal unitPrice;
    
    // Constructors
    public OrderItem() {
    }
    
    public OrderItem(Long productId, String productName, Integer quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    
    // Calculate line total
    public BigDecimal getLineTotal() {
        if (quantity == null || unitPrice == null) {
            return BigDecimal.ZERO;
        }
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
    
    // Getters and Setters
    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(productId, orderItem.productId) &&
               Objects.equals(quantity, orderItem.quantity) &&
               Objects.equals(unitPrice, orderItem.unitPrice);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity, unitPrice);
    }
    
    @Override
    public String toString() {
        return "OrderItem{" +
               "productId=" + productId +
               ", productName='" + productName + '\'' +
               ", quantity=" + quantity +
               ", unitPrice=" + unitPrice +
               ", lineTotal=" + getLineTotal() +
               '}';
    }
}