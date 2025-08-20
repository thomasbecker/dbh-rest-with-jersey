package com.dbh.training.rest.test;

import com.dbh.training.rest.dto.UserDTO;
import com.dbh.training.rest.dto.ProductDTO;
import com.dbh.training.rest.dto.OrderDTO;
import com.dbh.training.rest.models.User;
import com.dbh.training.rest.models.Product;
import com.dbh.training.rest.models.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Builder class for creating test data.
 * 
 * Provides fluent builders for creating test entities and DTOs
 * with sensible defaults and easy customization.
 */
public class TestDataBuilder {
    
    private static final AtomicLong idCounter = new AtomicLong(1000);
    
    /**
     * UserDTO Builder
     */
    public static class UserDTOBuilder {
        private String username = "testuser_" + UUID.randomUUID().toString().substring(0, 8);
        private String email = username + "@test.com";
        private String firstName = "Test";
        private String lastName = "User";
        private String role = "USER";
        private boolean active = true;
        
        public UserDTOBuilder withUsername(String username) {
            this.username = username;
            this.email = username + "@test.com";  // Update email to match
            return this;
        }
        
        public UserDTOBuilder withEmail(String email) {
            this.email = email;
            return this;
        }
        
        public UserDTOBuilder withName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
            return this;
        }
        
        public UserDTOBuilder withRole(String role) {
            this.role = role;
            return this;
        }
        
        public UserDTOBuilder inactive() {
            this.active = false;
            return this;
        }
        
        public UserDTO build() {
            UserDTO dto = new UserDTO();
            dto.setUsername(username);
            dto.setEmail(email);
            dto.setFirstName(firstName);
            dto.setLastName(lastName);
            dto.setRole(User.Role.valueOf(role));
            dto.setActive(active);
            return dto;
        }
    }
    
    /**
     * User Entity Builder
     */
    public static class UserBuilder {
        private Long id = idCounter.getAndIncrement();
        private String username = "testuser_" + UUID.randomUUID().toString().substring(0, 8);
        private String email = username + "@test.com";
        private String password = "password123";
        private String firstName = "Test";
        private String lastName = "User";
        private User.Role role = User.Role.USER;
        private boolean active = true;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();
        
        public UserBuilder withId(Long id) {
            this.id = id;
            return this;
        }
        
        public UserBuilder withUsername(String username) {
            this.username = username;
            this.email = username + "@test.com";
            return this;
        }
        
        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }
        
        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }
        
        public UserBuilder withName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
            return this;
        }
        
        public UserBuilder withRole(User.Role role) {
            this.role = role;
            return this;
        }
        
        public UserBuilder inactive() {
            this.active = false;
            return this;
        }
        
        public User build() {
            User user = new User.Builder()
                .id(id)
                .username(username)
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .role(role)
                .active(active)
                .build();
            
            // Set timestamps after building
            user.setCreatedAt(createdAt);
            user.setUpdatedAt(updatedAt);
            
            return user;
        }
    }
    
    /**
     * ProductDTO Builder
     */
    public static class ProductDTOBuilder {
        private String name = "Test Product " + UUID.randomUUID().toString().substring(0, 8);
        private String description = "Test product description";
        private BigDecimal price = new BigDecimal("99.99");
        private String sku = "SKU-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        private Integer stockQuantity = 100;
        private String category = "TEST";
        private boolean available = true;
        
        public ProductDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }
        
        public ProductDTOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }
        
        public ProductDTOBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }
        
        public ProductDTOBuilder withPrice(String price) {
            this.price = new BigDecimal(price);
            return this;
        }
        
        public ProductDTOBuilder withSku(String sku) {
            this.sku = sku;
            return this;
        }
        
        public ProductDTOBuilder withStock(int stockQuantity) {
            this.stockQuantity = stockQuantity;
            return this;
        }
        
        public ProductDTOBuilder withCategory(String category) {
            this.category = category;
            return this;
        }
        
        public ProductDTOBuilder unavailable() {
            this.available = false;
            return this;
        }
        
        public ProductDTOBuilder outOfStock() {
            this.stockQuantity = 0;
            this.available = false;
            return this;
        }
        
        public ProductDTO build() {
            ProductDTO dto = new ProductDTO();
            dto.setName(name);
            dto.setDescription(description);
            dto.setPrice(price);
            dto.setSku(sku);
            dto.setStock(stockQuantity);
            dto.setCategory(category);
            dto.setAvailable(available);
            return dto;
        }
    }
    
    // OrderDTO Builder removed for now - too complex with nested DTOs
    
    // Factory methods for quick creation
    
    public static UserDTOBuilder aUserDTO() {
        return new UserDTOBuilder();
    }
    
    public static UserBuilder aUser() {
        return new UserBuilder();
    }
    
    public static ProductDTOBuilder aProductDTO() {
        return new ProductDTOBuilder();
    }
    
    
    // Preset builders for common scenarios
    
    public static UserDTO adminUserDTO() {
        return aUserDTO()
            .withUsername("admin")
            .withEmail("admin@test.com")
            .withName("Admin", "User")
            .withRole("ADMIN")
            .build();
    }
    
    public static UserDTO regularUserDTO() {
        return aUserDTO()
            .withUsername("john_doe")
            .withEmail("john@test.com")
            .withName("John", "Doe")
            .withRole("USER")
            .build();
    }
    
    public static ProductDTO sampleProductDTO() {
        return aProductDTO()
            .withName("Sample Product")
            .withDescription("A sample product for testing")
            .withPrice("49.99")
            .withSku("SAMPLE-001")
            .withStock(50)
            .withCategory("ELECTRONICS")
            .build();
    }
}