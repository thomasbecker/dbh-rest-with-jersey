package com.dbh.training.rest.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dbh.training.rest.filters.CORSFilter;
import com.dbh.training.rest.filters.LoggingFilter;

/**
 * Jersey configuration class that sets up:
 * - Package scanning for resources
 * - Jackson JSON provider
 * - Exception mappers
 * - Filters (CORS, Logging)
 * 
 * This replaces web.xml configuration in traditional servlet deployments.
 */
public class JerseyConfig extends ResourceConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(JerseyConfig.class);
    
    public JerseyConfig() {
        logger.info("Initializing Jersey configuration...");
        
        // Package scanning - Jersey will automatically find and register:
        // - Resources (classes with @Path)
        // - Providers (filters, exception mappers, etc.)
        packages("com.dbh.training.rest");
        
        // Register Jackson for JSON processing
        register(JacksonFeature.class);
        register(JacksonConfig.class);
        
        // Register filters (commented out for Exercise 02)
        // register(CORSFilter.class);
        // register(LoggingFilter.class);
        
        // Disable validation for Exercise 02 (will be enabled in Exercise 03)
        // property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        
        // Disable automatic Wadl generation (optional)
        property(ServerProperties.WADL_FEATURE_DISABLE, true);
        
        // Enable JSON processing errors to be sent to client (helpful for debugging)
        property(ServerProperties.PROCESSING_RESPONSE_ERRORS_ENABLED, true);
        
        logger.info("Jersey configuration initialized successfully");
        logger.info("Scanning packages: com.dbh.training.rest");
        logger.info("Features enabled: Jackson JSON, CORS, Request/Response Logging");
    }
}