package com.dbh.training.rest.models;

/**
 * Product entity representing an item in the inventory.
 * 
 * TODO: Exercise 03 - Add basic fields
 * TODO: Exercise 04b - Add validation annotations  
 * TODO: Exercise 06 - Configure BigDecimal serialization
 */
public class Product {
    
    // TODO: Add the following fields:
    // - id (Long)
    // - name (String)
    // - description (String)
    // - price (BigDecimal) - Use BigDecimal for money!
    // - category (String)
    // - stock (Integer)
    // - sku (String) - Stock Keeping Unit
    // - imageUrl (String)
    // - available (boolean) - calculated from stock
    // - createdAt (LocalDateTime)
    // - updatedAt (LocalDateTime)
    
    // TODO: Generate constructors
    
    // TODO: Generate getters and setters
    
    // TODO: Add business logic methods:
    // - updateAvailability() - set available based on stock
    // - decreaseStock(int quantity)
    // - increaseStock(int quantity)
    
    // TODO: Override equals() and hashCode() based on id and sku
    
    // TODO: Override toString() for debugging
}