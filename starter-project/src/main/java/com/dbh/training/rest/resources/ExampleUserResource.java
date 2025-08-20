package com.dbh.training.rest.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Example User Resource to help you get started.
 * 
 * This is a minimal example showing basic structure.
 * You'll create your own complete UserResource in Exercise 03.
 * 
 * Try it out:
 * - GET http://localhost:8080/api/example/users
 * - GET http://localhost:8080/api/example/users/1
 */
@Path("/example/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExampleUserResource extends AbstractResource {
    
    // Simple in-memory storage for demonstration
    private static final Map<Long, Map<String, Object>> users = new HashMap<>();
    
    static {
        // Add a sample user
        Map<String, Object> user = new HashMap<>();
        user.put("id", 1L);
        user.put("username", "example_user");
        user.put("email", "example@test.com");
        users.put(1L, user);
    }
    
    /**
     * Get all users - simplest possible implementation.
     */
    @GET
    public Response getUsers() {
        logger.info("Getting all users");
        return ok(users.values());
    }
    
    /**
     * Get a single user by ID.
     */
    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id) {
        logger.info("Getting user with id: {}", id);
        
        Map<String, Object> user = users.get(id);
        if (user == null) {
            // For now, return a simple 404
            // You'll improve this with proper exception handling
            return Response.status(Response.Status.NOT_FOUND)
                .entity("User not found")
                .build();
        }
        
        return ok(user);
    }
    
    // TODO: Exercise 03 - Create your own UserResource with:
    // - POST to create users
    // - PUT to update users  
    // - DELETE to remove users
    // - Proper error handling
    // - Use the User model (after you complete it)
}