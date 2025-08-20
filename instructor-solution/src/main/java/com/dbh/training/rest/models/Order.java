package com.dbh.training.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Order entity representing a customer order.
 * 
 * This model demonstrates:
 * - Complex relationships (User, Products via OrderItems)
 * - Nested validation with @Valid
 * - Order lifecycle with status enum
 * - Calculated fields (totalAmount)
 */
public class Order {
    
    private Long id;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    private List<OrderItem> orderItems = new ArrayList<>();
    
    @DecimalMin(value = "0.01", message = "Total amount must be at least 0.01")
    @Digits(integer = 8, fraction = 2, message = "Total amount format is invalid")
    private BigDecimal totalAmount;
    
    @NotNull(message = "Order status is required")
    private OrderStatus status;
    
    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;
    
    @NotNull(message = "Shipping address is required")
    @Valid
    private ShippingAddress shippingAddress;
    
    private String orderNumber;
    
    private String notes;
    
    @PastOrPresent(message = "Order date cannot be in the future")
    private LocalDateTime orderDate;
    
    private LocalDateTime shippedDate;
    
    private LocalDateTime deliveredDate;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    // Order status enum
    public enum OrderStatus {
        PENDING("Pending", "Order has been placed but not processed"),
        PROCESSING("Processing", "Order is being prepared"),
        SHIPPED("Shipped", "Order has been shipped"),
        DELIVERED("Delivered", "Order has been delivered"),
        CANCELLED("Cancelled", "Order has been cancelled"),
        REFUNDED("Refunded", "Order has been refunded");
        
        private final String displayName;
        private final String description;
        
        OrderStatus(String displayName, String description) {
            this.displayName = displayName;
            this.description = description;
        }
        
        public String getDisplayName() {
            return displayName;
        }
        
        public String getDescription() {
            return description;
        }
        
        public boolean canTransitionTo(OrderStatus newStatus) {
            switch (this) {
                case PENDING:
                    return newStatus == PROCESSING || newStatus == CANCELLED;
                case PROCESSING:
                    return newStatus == SHIPPED || newStatus == CANCELLED;
                case SHIPPED:
                    return newStatus == DELIVERED || newStatus == REFUNDED;
                case DELIVERED:
                    return newStatus == REFUNDED;
                default:
                    return false;
            }
        }
    }
    
    // Payment method enum
    public enum PaymentMethod {
        CREDIT_CARD("Credit Card"),
        DEBIT_CARD("Debit Card"),
        PAYPAL("PayPal"),
        BANK_TRANSFER("Bank Transfer"),
        CASH_ON_DELIVERY("Cash on Delivery");
        
        private final String displayName;
        
        PaymentMethod(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    // Constructors
    public Order() {
        this.orderDate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }
    
    // Business logic methods
    public void calculateTotalAmount() {
        this.totalAmount = orderItems.stream()
            .map(OrderItem::getLineTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public void addOrderItem(OrderItem item) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        orderItems.add(item);
        calculateTotalAmount();
        updateTimestamp();
    }
    
    public void removeOrderItem(OrderItem item) {
        if (orderItems != null) {
            orderItems.remove(item);
            calculateTotalAmount();
            updateTimestamp();
        }
    }
    
    public boolean updateStatus(OrderStatus newStatus) {
        if (status != null && !status.canTransitionTo(newStatus)) {
            return false;
        }
        this.status = newStatus;
        
        // Update relevant dates
        if (newStatus == OrderStatus.SHIPPED) {
            this.shippedDate = LocalDateTime.now();
        } else if (newStatus == OrderStatus.DELIVERED) {
            this.deliveredDate = LocalDateTime.now();
        }
        
        updateTimestamp();
        return true;
    }
    
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }
    
    public void generateOrderNumber() {
        if (id != null) {
            this.orderNumber = String.format("ORD-%06d", id);
        }
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
        generateOrderNumber();
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        calculateTotalAmount();
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public OrderStatus getStatus() {
        return status;
    }
    
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }
    
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    
    public String getOrderNumber() {
        return orderNumber;
    }
    
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    
    public LocalDateTime getShippedDate() {
        return shippedDate;
    }
    
    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }
    
    public LocalDateTime getDeliveredDate() {
        return deliveredDate;
    }
    
    public void setDeliveredDate(LocalDateTime deliveredDate) {
        this.deliveredDate = deliveredDate;
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
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
               Objects.equals(orderNumber, order.orderNumber);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, orderNumber);
    }
    
    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", orderNumber='" + orderNumber + '\'' +
               ", userId=" + userId +
               ", status=" + status +
               ", totalAmount=" + totalAmount +
               ", itemCount=" + (orderItems != null ? orderItems.size() : 0) +
               ", orderDate=" + orderDate +
               '}';
    }
}