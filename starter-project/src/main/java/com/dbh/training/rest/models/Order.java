package com.dbh.training.rest.models;

/**
 * Order entity representing a customer order.
 * 
 * TODO: Exercise 03 - Add basic fields
 * TODO: Exercise 04b - Add validation annotations
 * TODO: Exercise 06 - Handle nested object serialization
 */
public class Order {
    
    // TODO: Add the following fields:
    // - id (Long)
    // - userId (Long) - reference to User
    // - orderItems (List<OrderItem>) - list of items in order
    // - totalAmount (BigDecimal)
    // - status (String or enum - PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED)
    // - paymentMethod (String or enum)
    // - shippingAddress (embedded object)
    // - orderNumber (String) - generated
    // - notes (String)
    // - orderDate (LocalDateTime)
    // - shippedDate (LocalDateTime)
    // - deliveredDate (LocalDateTime)
    // - createdAt (LocalDateTime)
    // - updatedAt (LocalDateTime)
    
    // TODO: Create OrderStatus enum with proper state transitions
    
    // TODO: Create PaymentMethod enum
    
    // TODO: Generate constructors
    
    // TODO: Generate getters and setters
    
    // TODO: Add business logic methods:
    // - calculateTotalAmount()
    // - addOrderItem(OrderItem item)
    // - removeOrderItem(OrderItem item)
    // - updateStatus(OrderStatus newStatus) - validate transitions
    // - generateOrderNumber()
    
    // TODO: Override equals() and hashCode() based on id and orderNumber
    
    // TODO: Override toString() for debugging
}