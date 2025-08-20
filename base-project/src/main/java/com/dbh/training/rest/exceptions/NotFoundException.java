package com.dbh.training.rest.exceptions;

/**
 * Exception thrown when a requested resource is not found.
 * This will be mapped to HTTP 404 status code.
 */
public class NotFoundException extends RuntimeException {
    
    private final String resourceType;
    private final String identifier;
    
    public NotFoundException(String message) {
        super(message);
        this.resourceType = null;
        this.identifier = null;
    }
    
    public NotFoundException(String resourceType, String identifier) {
        super(String.format("%s not found with identifier: %s", resourceType, identifier));
        this.resourceType = resourceType;
        this.identifier = identifier;
    }
    
    public NotFoundException(String resourceType, Long id) {
        this(resourceType, String.valueOf(id));
    }
    
    public String getResourceType() {
        return resourceType;
    }
    
    public String getIdentifier() {
        return identifier;
    }
}