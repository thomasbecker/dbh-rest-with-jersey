package com.dbh.training.rest.dto;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Standard error response for REST API.
 * 
 * This DTO is provided as infrastructure to handle errors consistently.
 * You'll create other DTOs during the exercises.
 */
public class ErrorResponse {
    private String errorId;
    private int status;
    private String error;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> validationErrors;
    private String debugMessage;
    
    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }
    
    public ErrorResponse(String errorId, int status, String error, String message, LocalDateTime timestamp) {
        this.errorId = errorId;
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }
    
    // Getters and Setters
    public String getErrorId() {
        return errorId;
    }
    
    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getError() {
        return error;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
    
    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
    
    public String getDebugMessage() {
        return debugMessage;
    }
    
    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
}