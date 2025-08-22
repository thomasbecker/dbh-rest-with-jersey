# Exercise 02: Jersey CRUD - Solution

## Complete Implementation

```java
package com.dbh.training.rest.resources;

import com.dbh.training.rest.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    
    private static final Map<Long, User> users = new ConcurrentHashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);
    
    @Context
    private UriInfo uriInfo;
    
    @GET
    public Response getAllUsers(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("0") int size,
            @QueryParam("username") String username,
            @QueryParam("emailDomain") String emailDomain) {
        
        List<User> userList = new ArrayList<>(users.values());
        
        // Apply filters
        if (username != null) {
            userList = userList.stream()
                .filter(u -> u.getUsername().equals(username))
                .collect(Collectors.toList());
        }
        
        if (emailDomain != null) {
            userList = userList.stream()
                .filter(u -> u.getEmail().endsWith("@" + emailDomain))
                .collect(Collectors.toList());
        }
        
        // Apply pagination if requested
        if (size > 0) {
            int totalCount = userList.size();
            int start = page * size;
            int end = Math.min(start + size, totalCount);
            
            if (start < totalCount) {
                userList = userList.subList(start, end);
            } else {
                userList = Collections.emptyList();
            }
            
            return Response.ok(userList)
                .header("X-Total-Count", totalCount)
                .header("X-Page", page)
                .header("X-Page-Size", size)
                .build();
        }
        
        return Response.ok(userList).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        User user = users.get(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }
    
    @POST
    public Response createUser(User user) {
        Long id = idGenerator.getAndIncrement();
        user.setId(id);
        user.setCreatedAt(LocalDateTime.now());
        users.put(id, user);
        
        URI location = uriInfo.getAbsolutePathBuilder()
            .path(String.valueOf(id))
            .build();
        
        return Response.created(location).entity(user).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, User user) {
        if (!users.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        user.setId(id);
        User existing = users.get(id);
        user.setCreatedAt(existing.getCreatedAt()); // Preserve creation date
        users.put(id, user);
        
        return Response.ok(user).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        User removed = users.remove(id);
        if (removed == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
    
    // ===== BONUS IMPLEMENTATIONS =====
    
    @PATCH
    @Path("/{id}")
    public Response patchUser(@PathParam("id") Long id, Map<String, Object> updates) {
        User user = users.get(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        // Apply partial updates
        if (updates.containsKey("username")) {
            user.setUsername((String) updates.get("username"));
        }
        if (updates.containsKey("email")) {
            user.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("firstName")) {
            user.setFirstName((String) updates.get("firstName"));
        }
        if (updates.containsKey("lastName")) {
            user.setLastName((String) updates.get("lastName"));
        }
        
        return Response.ok(user).build();
    }
    
    @GET
    @Path("/search")
    public Response searchUsers(@QueryParam("q") String query) {
        if (query == null || query.trim().isEmpty()) {
            return Response.ok(Collections.emptyList()).build();
        }
        
        String searchTerm = query.toLowerCase();
        List<User> results = users.values().stream()
            .filter(u -> 
                u.getUsername().toLowerCase().contains(searchTerm) ||
                u.getFirstName().toLowerCase().contains(searchTerm) ||
                u.getLastName().toLowerCase().contains(searchTerm))
            .collect(Collectors.toList());
        
        return Response.ok(results).build();
    }
}
```

## User Model Implementation

```java
package com.dbh.training.rest.models;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    
    // Default constructor
    public User() {}
    
    // Constructor with fields
    public User(String username, String email, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
```

## Key Learning Points

### 1. Thread Safety
- Used `ConcurrentHashMap` for thread-safe storage
- Used `AtomicLong` for thread-safe ID generation

### 2. HTTP Status Codes
- 200 OK - Successful GET/PUT
- 201 Created - Successful POST with Location header
- 204 No Content - Successful DELETE
- 404 Not Found - Resource doesn't exist

### 3. REST Best Practices
- Resource-oriented URLs (`/users`, `/users/{id}`)
- Proper HTTP methods for each operation
- Location header on resource creation
- Consistent JSON responses

### 4. JAX-RS Annotations
- `@Path` - Define resource paths
- `@GET`, `@POST`, `@PUT`, `@DELETE`, `@PATCH` - HTTP methods
- `@PathParam` - Extract path parameters
- `@QueryParam` - Extract query parameters
- `@DefaultValue` - Default values for parameters
- `@Produces`/`@Consumes` - Content type handling
- `@Context` - Inject context objects like UriInfo

### 5. Response Building
```java
// Different response patterns
Response.ok(entity).build()                    // 200 with body
Response.created(uri).entity(entity).build()   // 201 with Location
Response.noContent().build()                   // 204 no body
Response.status(404).build()                   // 404 error
```

## Common Issues and Solutions

### Issue 1: Tests fail with 404 on all endpoints
**Solution:** Ensure `@Path("/users")` is on the class and the resource is registered in JerseyConfig.

### Issue 2: Location header is missing or incorrect
**Solution:** Use `UriInfo` to build proper URIs:
```java
@Context
private UriInfo uriInfo;

URI location = uriInfo.getAbsolutePathBuilder()
    .path(String.valueOf(id))
    .build();
```

### Issue 3: Concurrent modification exceptions
**Solution:** Use `ConcurrentHashMap` instead of regular `HashMap`.

### Issue 4: JSON serialization issues with LocalDateTime
**Solution:** Register JavaTimeModule with Jackson:
```java
ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new JavaTimeModule());
```

## Testing the Implementation

Run all tests:
```bash
./gradlew test --tests UserResourceTest
```

Test individual endpoints with curl:
```bash
# GET all users
curl -X GET http://localhost:8080/api/users

# POST new user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"username":"test","email":"test@example.com","firstName":"Test","lastName":"User"}'

# GET specific user
curl -X GET http://localhost:8080/api/users/1

# PUT update user
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"username":"updated","email":"new@example.com","firstName":"Updated","lastName":"User"}'

# DELETE user
curl -X DELETE http://localhost:8080/api/users/1
```

## Extension Ideas

1. **Add validation** (Exercise 03)
2. **Add exception handling** for better error responses
3. **Add logging** for debugging
4. **Add database persistence** instead of in-memory storage
5. **Add HATEOAS links** for discoverability