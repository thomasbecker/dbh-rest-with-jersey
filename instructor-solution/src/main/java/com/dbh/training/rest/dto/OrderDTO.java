package com.dbh.training.rest.dto;

import com.dbh.training.rest.models.Order;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    
    private Long id;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    private List<OrderItemDTO> orderItems = new ArrayList<>();
    
    @DecimalMin(value = "0.01", message = "Total amount must be at least 0.01")
    @Digits(integer = 8, fraction = 2, message = "Total amount format is invalid")
    private BigDecimal totalAmount;
    
    @NotNull(message = "Order status is required")
    private Order.OrderStatus status;
    
    @NotNull(message = "Payment method is required")
    private Order.PaymentMethod paymentMethod;
    
    @NotNull(message = "Shipping address is required")
    @Valid
    private ShippingAddressDTO shippingAddress;
    
    private String orderNumber;
    
    private String notes;
    
    @PastOrPresent(message = "Order date cannot be in the future")
    private LocalDateTime orderDate;
    
    private LocalDateTime shippedDate;
    
    private LocalDateTime deliveredDate;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    private String statusDisplayName;
    
    private String paymentMethodDisplayName;
    
    public OrderDTO() {
    }
    
    public static OrderDTO fromEntity(Order order) {
        if (order == null) {
            return null;
        }
        
        OrderDTO dto = new OrderDTO();
        dto.id = order.getId();
        dto.userId = order.getUserId();
        
        if (order.getOrderItems() != null) {
            dto.orderItems = order.getOrderItems().stream()
                .map(OrderItemDTO::fromEntity)
                .collect(Collectors.toList());
        }
        
        dto.totalAmount = order.getTotalAmount();
        dto.status = order.getStatus();
        dto.paymentMethod = order.getPaymentMethod();
        dto.shippingAddress = ShippingAddressDTO.fromEntity(order.getShippingAddress());
        dto.orderNumber = order.getOrderNumber();
        dto.notes = order.getNotes();
        dto.orderDate = order.getOrderDate();
        dto.shippedDate = order.getShippedDate();
        dto.deliveredDate = order.getDeliveredDate();
        dto.createdAt = order.getCreatedAt();
        dto.updatedAt = order.getUpdatedAt();
        
        if (order.getStatus() != null) {
            dto.statusDisplayName = order.getStatus().getDisplayName();
        }
        if (order.getPaymentMethod() != null) {
            dto.paymentMethodDisplayName = order.getPaymentMethod().getDisplayName();
        }
        
        return dto;
    }
    
    public Order toEntity() {
        Order order = new Order();
        order.setId(this.id);
        order.setUserId(this.userId);
        
        if (this.orderItems != null) {
            order.setOrderItems(
                this.orderItems.stream()
                    .map(OrderItemDTO::toEntity)
                    .collect(Collectors.toList())
            );
        }
        
        order.setTotalAmount(this.totalAmount);
        order.setStatus(this.status);
        order.setPaymentMethod(this.paymentMethod);
        
        if (this.shippingAddress != null) {
            order.setShippingAddress(this.shippingAddress.toEntity());
        }
        
        order.setOrderNumber(this.orderNumber);
        order.setNotes(this.notes);
        order.setOrderDate(this.orderDate);
        order.setShippedDate(this.shippedDate);
        order.setDeliveredDate(this.deliveredDate);
        
        return order;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }
    
    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Order.OrderStatus getStatus() {
        return status;
    }
    
    public void setStatus(Order.OrderStatus status) {
        this.status = status;
    }
    
    public Order.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(Order.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public ShippingAddressDTO getShippingAddress() {
        return shippingAddress;
    }
    
    public void setShippingAddress(ShippingAddressDTO shippingAddress) {
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
    
    public String getStatusDisplayName() {
        return statusDisplayName;
    }
    
    public void setStatusDisplayName(String statusDisplayName) {
        this.statusDisplayName = statusDisplayName;
    }
    
    public String getPaymentMethodDisplayName() {
        return paymentMethodDisplayName;
    }
    
    public void setPaymentMethodDisplayName(String paymentMethodDisplayName) {
        this.paymentMethodDisplayName = paymentMethodDisplayName;
    }
}