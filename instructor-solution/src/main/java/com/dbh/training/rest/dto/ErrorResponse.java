package com.dbh.training.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Standard error response DTO for REST API errors.
 * 
 * Provides a consistent structure for error responses across the API.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    
    private String errorId;
    private Integer status;
    private String error;
    private String message;
    private LocalDateTime timestamp;
    private String path;
    private Map<String, String> validationErrors;
    private String debugMessage;
    
    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }
    
    public ErrorResponse(String errorId, Integer status, String error, String message, LocalDateTime timestamp) {
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
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
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
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
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