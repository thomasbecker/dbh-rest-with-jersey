package com.dbh.training.rest.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Exception thrown when validation fails.
 * This will be mapped to HTTP 400 status code.
 */
public class ValidationException extends RuntimeException {
    
    private final Map<String, String> errors;
    
    public ValidationException(String message) {
        super(message);
        this.errors = new HashMap<>();
    }
    
    public ValidationException(String field, String message) {
        super("Validation failed for field: " + field);
        this.errors = new HashMap<>();
        this.errors.put(field, message);
    }
    
    public ValidationException(Map<String, String> errors) {
        super("Validation failed with " + errors.size() + " error(s)");
        this.errors = new HashMap<>(errors);
    }
    
    public Map<String, String> getErrors() {
        return new HashMap<>(errors);
    }
    
    public void addError(String field, String message) {
        errors.put(field, message);
    }
    
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}