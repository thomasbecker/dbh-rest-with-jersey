package com.dbh.training.rest.mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.dbh.training.rest.dto.ErrorResponse;
import com.dbh.training.rest.exceptions.NotFoundException;
import com.dbh.training.rest.exceptions.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Global exception mapper that converts exceptions to appropriate HTTP responses.
 * 
 * This centralizes error handling and ensures consistent error responses
 * across the entire API.
 */
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionMapper.class);
    
    @Override
    public Response toResponse(Throwable exception) {
        String errorId = UUID.randomUUID().toString().substring(0, 8);
        
        // Log the exception
        logger.error("[{}] Exception caught: {}", errorId, exception.getMessage(), exception);
        
        ErrorResponse errorResponse;
        Response.Status status;
        
        // Handle specific exception types
        if (exception instanceof NotFoundException) {
            status = Response.Status.NOT_FOUND;
            errorResponse = new ErrorResponse(
                errorId,
                status.getStatusCode(),
                "Resource Not Found",
                exception.getMessage(),
                LocalDateTime.now()
            );
            
        } else if (exception instanceof ValidationException) {
            status = Response.Status.BAD_REQUEST;
            ValidationException validationEx = (ValidationException) exception;
            errorResponse = new ErrorResponse(
                errorId,
                status.getStatusCode(),
                "Validation Failed",
                exception.getMessage(),
                LocalDateTime.now()
            );
            errorResponse.setValidationErrors(validationEx.getErrors());
            
        } else if (exception instanceof IllegalArgumentException) {
            status = Response.Status.BAD_REQUEST;
            errorResponse = new ErrorResponse(
                errorId,
                status.getStatusCode(),
                "Invalid Request",
                exception.getMessage(),
                LocalDateTime.now()
            );
            
        } else if (exception instanceof SecurityException) {
            status = Response.Status.FORBIDDEN;
            errorResponse = new ErrorResponse(
                errorId,
                status.getStatusCode(),
                "Access Denied",
                "You don't have permission to access this resource",
                LocalDateTime.now()
            );
            
        } else {
            // Generic error handling
            status = Response.Status.INTERNAL_SERVER_ERROR;
            errorResponse = new ErrorResponse(
                errorId,
                status.getStatusCode(),
                "Internal Server Error",
                "An unexpected error occurred. Please try again later.",
                LocalDateTime.now()
            );
            
            // In development/training, include the actual error message
            // In production, you might want to hide this
            if (logger.isDebugEnabled()) {
                errorResponse.setDebugMessage(exception.getMessage());
            }
        }
        
        return Response
            .status(status)
            .entity(errorResponse)
            .type(MediaType.APPLICATION_JSON)
            .header("X-Error-Id", errorId)
            .build();
    }
}