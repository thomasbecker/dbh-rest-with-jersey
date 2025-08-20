package com.dbh.training.rest.dto;

import com.dbh.training.rest.models.OrderItem;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDTO {
    
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
    
    private BigDecimal lineTotal;
    
    public OrderItemDTO() {
    }
    
    public static OrderItemDTO fromEntity(OrderItem item) {
        if (item == null) {
            return null;
        }
        
        OrderItemDTO dto = new OrderItemDTO();
        dto.productId = item.getProductId();
        dto.productName = item.getProductName();
        dto.quantity = item.getQuantity();
        dto.unitPrice = item.getUnitPrice();
        dto.lineTotal = item.getLineTotal();
        return dto;
    }
    
    public OrderItem toEntity() {
        return new OrderItem(productId, productName, quantity, unitPrice);
    }
    
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
    
    public BigDecimal getLineTotal() {
        return lineTotal;
    }
    
    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }
}