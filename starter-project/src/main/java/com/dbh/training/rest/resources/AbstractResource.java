package com.dbh.training.rest.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * Base class for all REST resources.
 * 
 * This class provides common functionality that all resources will need.
 * 
 * TODO: Exercise 03 - Study the provided helper methods
 * TODO: Exercise 04 - Use these methods in your UserResource
 */
public abstract class AbstractResource {
    
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Context
    protected UriInfo uriInfo;
    
    /**
     * Build a location URI for a newly created resource.
     * Use this in POST methods to set the Location header.
     * 
     * @param id The ID of the created entity
     * @return URI for the new resource
     */
    protected URI buildLocationUri(Object id) {
        return uriInfo.getAbsolutePathBuilder()
            .path(String.valueOf(id))
            .build();
    }
    
    /**
     * Create a 201 Created response with Location header.
     * Use this when creating new resources with POST.
     * 
     * @param entity The created entity to return
     * @param id The ID of the created entity
     * @return Response with 201 status and Location header
     */
    protected Response created(Object entity, Object id) {
        return Response.created(buildLocationUri(id))
            .entity(entity)
            .build();
    }
    
    /**
     * Create a 200 OK response with entity.
     * Use this for successful GET, PUT, and PATCH operations.
     * 
     * @param entity The entity to return
     * @return Response with 200 status
     */
    protected Response ok(Object entity) {
        return Response.ok(entity).build();
    }
    
    /**
     * Create a 204 No Content response.
     * Use this for successful DELETE operations.
     * 
     * @return Response with 204 status
     */
    protected Response noContent() {
        return Response.noContent().build();
    }
    
    // TODO: Exercise 05 - Add a paginated() method that adds pagination headers
    // Hint: Headers to add: X-Page, X-Page-Size, X-Total-Count, X-Total-Pages
    
    // TODO: Exercise 07 - Add logging helper methods for debugging
    // Hint: logEntry() and logExit() methods that use logger.debug()
}