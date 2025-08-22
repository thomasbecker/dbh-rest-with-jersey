package com.dbh.training.rest.resources;

import com.dbh.training.rest.dto.UserDTO;
import com.dbh.training.rest.exceptions.NotFoundException;
import com.dbh.training.rest.exceptions.ValidationException;
import com.dbh.training.rest.models.User;
import com.dbh.training.rest.util.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * User REST resource with full CRUD operations.
 * 
 * This example demonstrates:
 * - All HTTP methods (GET, POST, PUT, PATCH, DELETE)
 * - Validation with @Valid
 * - Exception handling
 * - Pagination and filtering
 * - Proper status codes and headers
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource extends AbstractResource {
    
    // In-memory storage for demo purposes
    private static final Map<Long, User> users = new ConcurrentHashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);
    
    // Package-private method for test cleanup (only accessible from same package)
    static void resetForTesting() {
        users.clear();
        idGenerator.set(1);
    }
    
    // Initialize with sample data
    static {
        User user1 = new User.Builder()
            .id(idGenerator.getAndIncrement())
            .username("john_doe")
            .email("john@example.com")
            .password("password123")
            .firstName("John")
            .lastName("Doe")
            .role(User.Role.USER)
            .active(true)
            .build();
        users.put(user1.getId(), user1);
        
        User user2 = new User.Builder()
            .id(idGenerator.getAndIncrement())
            .username("jane_admin")
            .email("jane@example.com")
            .password("admin456")
            .firstName("Jane")
            .lastName("Admin")
            .role(User.Role.ADMIN)
            .active(true)
            .build();
        users.put(user2.getId(), user2);
    }
    
    /**
     * Get all users with optional pagination and filtering.
     * 
     * @param page Page number (0-based)
     * @param size Page size
     * @param role Filter by role
     * @param active Filter by active status
     * @return List of users
     */
    @GET
    public Response getUsers(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("role") String role,
            @QueryParam("active") Boolean active) {
        
        logEntry("getUsers", page, size, role, active);
        
        // Filter users
        List<User> filteredUsers = users.values().stream()
            .filter(u -> role == null || u.getRole().name().equalsIgnoreCase(role))
            .filter(u -> active == null || u.isActive() == active)
            .sorted(Comparator.comparing(User::getId))
            .collect(Collectors.toList());
        // Paginate
        int start = page * size;
        int end = Math.min(start + size, filteredUsers.size());
        
        if (start > filteredUsers.size()) {
            return paginated(Collections.emptyList(), page, size, filteredUsers.size());
        }
        
        List<User> pageUsers = filteredUsers.subList(start, end);
        List<UserDTO> dtos = ModelMapper.toUserDTOs(pageUsers);
        
        logExit("getUsers", dtos.size() + " users");
        return paginated(dtos, page, size, filteredUsers.size());
    }
    
    /**
     * Get a single user by ID.
     * 
     * @param id User ID
     * @return User details
     * @throws NotFoundException if user not found
     */
    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id) {
        logEntry("getUser", id);
        
        User user = users.get(id);
        if (user == null) {
            throw new NotFoundException("User not found with id: " + id);
        }
        
        UserDTO dto = ModelMapper.toUserDTO(user);
        logExit("getUser", dto);
        return ok(dto);
    }
    
    /**
     * Create a new user.
     * 
     * @param userDTO User data
     * @return Created user with 201 status
     * @throws ValidationException if validation fails
     */
    @POST
    public Response createUser(@Valid @NotNull UserDTO userDTO) {
        logEntry("createUser", userDTO);
        
        // Validate unique username
        boolean usernameExists = users.values().stream()
            .anyMatch(u -> u.getUsername().equalsIgnoreCase(userDTO.getUsername()));
        
        if (usernameExists) {
            Map<String, String> errors = new HashMap<>();
            errors.put("username", "Username already exists");
            throw new ValidationException(errors);
        }
        
        // Validate unique email
        boolean emailExists = users.values().stream()
            .anyMatch(u -> u.getEmail().equalsIgnoreCase(userDTO.getEmail()));
        
        if (emailExists) {
            Map<String, String> errors = new HashMap<>();
            errors.put("email", "Email already exists");
            throw new ValidationException(errors);
        }
        
        // Create user
        User user = ModelMapper.toUser(userDTO);
        user.setId(idGenerator.getAndIncrement());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        
        users.put(user.getId(), user);
        
        UserDTO createdDTO = ModelMapper.toUserDTO(user);
        logExit("createUser", createdDTO);
        return created(createdDTO, user.getId());
    }
    
    /**
     * Update an entire user (full replacement).
     * 
     * @param id User ID
     * @param userDTO Updated user data
     * @return Updated user
     * @throws NotFoundException if user not found
     */
    @PUT
    @Path("/{id}")
    public Response updateUser(
            @PathParam("id") Long id,
            @Valid @NotNull UserDTO userDTO) {
        
        logEntry("updateUser", id, userDTO);
        
        User existingUser = users.get(id);
        if (existingUser == null) {
            throw new NotFoundException("User not found with id: " + id);
        }
        
        // Check username uniqueness (excluding current user)
        boolean usernameExists = users.values().stream()
            .filter(u -> !u.getId().equals(id))
            .anyMatch(u -> u.getUsername().equalsIgnoreCase(userDTO.getUsername()));
        
        if (usernameExists) {
            Map<String, String> errors = new HashMap<>();
            errors.put("username", "Username already exists");
            throw new ValidationException(errors);
        }
        
        // Full update
        User updatedUser = ModelMapper.toUser(userDTO);
        updatedUser.setId(id);
        updatedUser.setCreatedAt(existingUser.getCreatedAt());
        updatedUser.setUpdatedAt(LocalDateTime.now());
        
        users.put(id, updatedUser);
        
        UserDTO updatedDTO = ModelMapper.toUserDTO(updatedUser);
        logExit("updateUser", updatedDTO);
        return ok(updatedDTO);
    }
    
    /**
     * Partially update a user.
     * 
     * @param id User ID
     * @param updates Map of fields to update
     * @return Updated user
     * @throws NotFoundException if user not found
     */
    @PATCH
    @Path("/{id}")
    public Response patchUser(
            @PathParam("id") Long id,
            Map<String, Object> updates) {
        
        logEntry("patchUser", id, updates);
        
        User user = users.get(id);
        if (user == null) {
            throw new NotFoundException("User not found with id: " + id);
        }
        
        // Apply updates
        updates.forEach((key, value) -> {
            switch (key) {
                case "firstName":
                    user.setFirstName((String) value);
                    break;
                case "lastName":
                    user.setLastName((String) value);
                    break;
                case "email":
                    // Validate email uniqueness
                    String newEmail = (String) value;
                    boolean emailExists = users.values().stream()
                        .filter(u -> !u.getId().equals(id))
                        .anyMatch(u -> u.getEmail().equalsIgnoreCase(newEmail));
                    
                    if (emailExists) {
                        Map<String, String> errors = new HashMap<>();
                        errors.put("email", "Email already exists");
                        throw new ValidationException(errors);
                    }
                    user.setEmail(newEmail);
                    break;
                case "active":
                    user.setActive((Boolean) value);
                    break;
                case "role":
                    user.setRole(User.Role.valueOf((String) value));
                    break;
                default:
                    logger.warn("Unknown field in patch: {}", key);
            }
        });
        
        user.setUpdatedAt(LocalDateTime.now());
        
        UserDTO updatedDTO = ModelMapper.toUserDTO(user);
        logExit("patchUser", updatedDTO);
        return ok(updatedDTO);
    }
    
    /**
     * Delete a user.
     * 
     * @param id User ID
     * @return 204 No Content
     * @throws NotFoundException if user not found
     */
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        logEntry("deleteUser", id);
        
        User removed = users.remove(id);
        if (removed == null) {
            throw new NotFoundException("User not found with id: " + id);
        }
        
        logExit("deleteUser", "deleted");
        return noContent();
    }
    
    /**
     * Search users by username or email.
     * 
     * @param query Search query
     * @return Matching users
     */
    @GET
    @Path("/search")
    public Response searchUsers(@QueryParam("q") String query) {
        logEntry("searchUsers", query);
        
        if (query == null || query.trim().isEmpty()) {
            return ok(Collections.emptyList());
        }
        
        String lowerQuery = query.toLowerCase();
        List<User> results = users.values().stream()
            .filter(u -> u.getUsername().toLowerCase().contains(lowerQuery) ||
                        u.getEmail().toLowerCase().contains(lowerQuery) ||
                        u.getFirstName().toLowerCase().contains(lowerQuery) ||
                        u.getLastName().toLowerCase().contains(lowerQuery))
            .collect(Collectors.toList());
        
        List<UserDTO> dtos = ModelMapper.toUserDTOs(results);
        logExit("searchUsers", dtos.size() + " results");
        return ok(dtos);
    }
}
