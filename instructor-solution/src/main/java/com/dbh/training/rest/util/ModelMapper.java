package com.dbh.training.rest.util;

import com.dbh.training.rest.dto.*;
import com.dbh.training.rest.models.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for mapping between entities and DTOs.
 * 
 * This demonstrates manual mapping approaches for training purposes.
 * In production, libraries like MapStruct or ModelMapper could be used.
 */
public class ModelMapper {
    
    private ModelMapper() {
        // Private constructor to prevent instantiation
    }
    
    // User mappings
    public static UserDTO toUserDTO(User user) {
        return UserDTO.fromEntity(user);
    }
    
    public static User toUser(UserDTO dto) {
        return dto != null ? dto.toEntity() : null;
    }
    
    public static List<UserDTO> toUserDTOs(List<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream()
            .map(UserDTO::fromEntity)
            .collect(Collectors.toList());
    }
    
    // Product mappings
    public static ProductDTO toProductDTO(Product product) {
        return ProductDTO.fromEntity(product);
    }
    
    public static Product toProduct(ProductDTO dto) {
        return dto != null ? dto.toEntity() : null;
    }
    
    public static List<ProductDTO> toProductDTOs(List<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
            .map(ProductDTO::fromEntity)
            .collect(Collectors.toList());
    }
    
    // Order mappings
    public static OrderDTO toOrderDTO(Order order) {
        return OrderDTO.fromEntity(order);
    }
    
    public static Order toOrder(OrderDTO dto) {
        return dto != null ? dto.toEntity() : null;
    }
    
    public static List<OrderDTO> toOrderDTOs(List<Order> orders) {
        if (orders == null) {
            return null;
        }
        return orders.stream()
            .map(OrderDTO::fromEntity)
            .collect(Collectors.toList());
    }
    
    // OrderItem mappings
    public static OrderItemDTO toOrderItemDTO(OrderItem item) {
        return OrderItemDTO.fromEntity(item);
    }
    
    public static OrderItem toOrderItem(OrderItemDTO dto) {
        return dto != null ? dto.toEntity() : null;
    }
    
    public static List<OrderItemDTO> toOrderItemDTOs(List<OrderItem> items) {
        if (items == null) {
            return null;
        }
        return items.stream()
            .map(OrderItemDTO::fromEntity)
            .collect(Collectors.toList());
    }
    
    // ShippingAddress mappings
    public static ShippingAddressDTO toShippingAddressDTO(ShippingAddress address) {
        return ShippingAddressDTO.fromEntity(address);
    }
    
    public static ShippingAddress toShippingAddress(ShippingAddressDTO dto) {
        return dto != null ? dto.toEntity() : null;
    }
    
    /**
     * Update an existing entity from a DTO, preserving fields that shouldn't change.
     * Useful for PATCH operations where only some fields are updated.
     */
    public static void updateUserFromDTO(User user, UserDTO dto) {
        if (user == null || dto == null) {
            return;
        }
        
        // Update only modifiable fields
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }
        user.setActive(dto.isActive());
        user.updateTimestamp();
    }
    
    /**
     * Update an existing product from a DTO.
     */
    public static void updateProductFromDTO(Product product, ProductDTO dto) {
        if (product == null || dto == null) {
            return;
        }
        
        if (dto.getName() != null) {
            product.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            product.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if (dto.getCategory() != null) {
            product.setCategory(dto.getCategory());
        }
        if (dto.getStock() != null) {
            product.setStock(dto.getStock());
        }
        if (dto.getSku() != null) {
            product.setSku(dto.getSku());
        }
        if (dto.getImageUrl() != null) {
            product.setImageUrl(dto.getImageUrl());
        }
        product.updateTimestamp();
    }
    
    /**
     * Update an existing order from a DTO.
     * Note: Some fields like status transitions should use business logic methods.
     */
    public static void updateOrderFromDTO(Order order, OrderDTO dto) {
        if (order == null || dto == null) {
            return;
        }
        
        if (dto.getOrderItems() != null) {
            order.setOrderItems(
                dto.getOrderItems().stream()
                    .map(OrderItemDTO::toEntity)
                    .collect(Collectors.toList())
            );
        }
        if (dto.getPaymentMethod() != null) {
            order.setPaymentMethod(dto.getPaymentMethod());
        }
        if (dto.getShippingAddress() != null) {
            order.setShippingAddress(dto.getShippingAddress().toEntity());
        }
        if (dto.getNotes() != null) {
            order.setNotes(dto.getNotes());
        }
        
        // Status changes should use the business logic method
        if (dto.getStatus() != null && !dto.getStatus().equals(order.getStatus())) {
            order.updateStatus(dto.getStatus());
        }
        
        order.updateTimestamp();
    }
}