package com.dbh.training.rest.resources;

import com.dbh.training.rest.dto.UserDTO;
import com.dbh.training.rest.test.BaseIntegrationTest;
import com.dbh.training.rest.test.TestDataBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.HashMap;
import java.util.Map;

import static com.dbh.training.rest.test.RestAssertions.assertThat;
import static com.dbh.training.rest.test.TestDataBuilder.*;
import static org.hamcrest.Matchers.*;

/**
 * Integration tests for UserResource.
 * 
 * Tests all CRUD operations and edge cases for the User REST API.
 */
@DisplayName("User Resource Integration Tests")
public class UserResourceIntegrationTest extends BaseIntegrationTest {
    
    @Nested
    @DisplayName("GET /users")
    class GetAllUsers {
        
        @Test
        @DisplayName("Should return all users with default pagination")
        void shouldReturnAllUsers() {
            Response response = given()
                .get("/users");
            
            assertThat(response)
                .isOk()
                .hasJsonContentType()
                .hasPaginationHeaders()
                .hasPage(0)
                .hasPageSize(10)
                .isNotEmpty();
        }
        
        @Test
        @DisplayName("Should filter users by role")
        void shouldFilterByRole() {
            Response response = given()
                .queryParam("role", "ADMIN")
                .get("/users");
            
            assertThat(response)
                .isOk()
                .and()
                .body("$.every { it.role == 'ADMIN' }", is(true));
        }
        
        @Test
        @DisplayName("Should filter users by active status")
        void shouldFilterByActiveStatus() {
            Response response = given()
                .queryParam("active", false)
                .get("/users");
            
            assertThat(response)
                .isOk()
                .and()
                .body("$.every { it.active == false }", is(true));
        }
        
        @Test
        @DisplayName("Should paginate results")
        void shouldPaginateResults() {
            Response response = given()
                .queryParam("page", 0)
                .queryParam("size", 1)
                .get("/users");
            
            assertThat(response)
                .isOk()
                .hasPageSize(1)
                .hasSize(1);
        }
    }
    
    @Nested
    @DisplayName("GET /users/{id}")
    class GetUserById {
        
        @Test
        @DisplayName("Should return user when exists")
        void shouldReturnUser() {
            Response response = given()
                .get("/users/1");
            
            assertThat(response)
                .isOk()
                .hasJsonContentType()
                .hasUserId()
                .hasNoPassword()
                .hasFieldNotNull("username")
                .hasFieldNotNull("email");
        }
        
        @Test
        @DisplayName("Should return 404 when user not found")
        void shouldReturn404WhenNotFound() {
            Response response = given()
                .get("/users/99999");
            
            assertThat(response)
                .isNotFound()
                .hasErrorContaining("not found");
        }
    }
    
    @Nested
    @DisplayName("POST /users")
    class CreateUser {
        
        @Test
        @DisplayName("Should create new user successfully")
        void shouldCreateUser() {
            UserDTO newUser = aUserDTO()
                .withUsername("newuser")
                .withEmail("newuser@test.com")
                .withName("New", "User")
                .build();
            
            Response response = given()
                .body(newUser)
                .post("/users");
            
            assertThat(response)
                .isCreated()
                .hasLocationHeader("/users/")
                .hasUser("newuser", "newuser@test.com")
                .hasUserId()
                .hasNoPassword();
        }
        
        @Test
        @DisplayName("Should reject duplicate username")
        void shouldRejectDuplicateUsername() {
            UserDTO user = aUserDTO()
                .withUsername("john_doe")  // Existing username
                .withEmail("different@test.com")
                .build();
            
            Response response = given()
                .body(user)
                .post("/users");
            
            assertThat(response)
                .isBadRequest()
                .hasValidationError("username", "Username already exists");
        }
        
        @Test
        @DisplayName("Should reject duplicate email")
        void shouldRejectDuplicateEmail() {
            UserDTO user = aUserDTO()
                .withUsername("different_user")
                .withEmail("john@example.com")  // Existing email
                .build();
            
            Response response = given()
                .body(user)
                .post("/users");
            
            assertThat(response)
                .isBadRequest()
                .hasValidationError("email", "Email already exists");
        }
        
        @Test
        @DisplayName("Should validate required fields")
        void shouldValidateRequiredFields() {
            UserDTO invalidUser = new UserDTO();  // Empty DTO
            
            Response response = given()
                .body(invalidUser)
                .post("/users");
            
            assertThat(response)
                .isBadRequest()
                .hasValidationErrors();
        }
    }
    
    @Nested
    @DisplayName("PUT /users/{id}")
    class UpdateUser {
        
        @Test
        @DisplayName("Should update existing user")
        void shouldUpdateUser() {
            UserDTO update = aUserDTO()
                .withUsername("john_doe")
                .withEmail("john.updated@example.com")
                .withName("John", "Updated")
                .build();
            
            Response response = given()
                .body(update)
                .put("/users/1");
            
            assertThat(response)
                .isOk()
                .hasField("email", "john.updated@example.com")
                .hasField("lastName", "Updated");
        }
        
        @Test
        @DisplayName("Should return 404 when updating non-existent user")
        void shouldReturn404WhenUpdatingNonExistent() {
            UserDTO update = regularUserDTO();
            
            Response response = given()
                .body(update)
                .put("/users/99999");
            
            assertThat(response)
                .isNotFound()
                .hasErrorContaining("not found");
        }
        
        @Test
        @DisplayName("Should prevent duplicate username on update")
        void shouldPreventDuplicateUsernameOnUpdate() {
            UserDTO update = aUserDTO()
                .withUsername("jane_admin")  // Try to use existing username
                .build();
            
            Response response = given()
                .body(update)
                .put("/users/1");
            
            assertThat(response)
                .isBadRequest()
                .hasValidationError("username", "Username already exists");
        }
    }
    
    @Nested
    @DisplayName("PATCH /users/{id}")
    class PatchUser {
        
        @Test
        @DisplayName("Should partially update user")
        void shouldPartiallyUpdateUser() {
            Map<String, Object> patch = new HashMap<>();
            patch.put("firstName", "Jonathan");
            patch.put("active", false);
            
            Response response = given()
                .body(patch)
                .patch("/users/1");
            
            assertThat(response)
                .isOk()
                .hasField("firstName", "Jonathan")
                .hasField("active", false)
                .hasField("lastName", "Doe");  // Unchanged
        }
        
        @Test
        @DisplayName("Should validate email uniqueness on patch")
        void shouldValidateEmailUniquenessOnPatch() {
            Map<String, Object> patch = new HashMap<>();
            patch.put("email", "jane@example.com");  // Existing email
            
            Response response = given()
                .body(patch)
                .patch("/users/1");
            
            assertThat(response)
                .isBadRequest()
                .hasValidationError("email", "Email already exists");
        }
        
        @Test
        @DisplayName("Should ignore unknown fields in patch")
        void shouldIgnoreUnknownFields() {
            Map<String, Object> patch = new HashMap<>();
            patch.put("unknownField", "value");
            patch.put("firstName", "John");
            
            Response response = given()
                .body(patch)
                .patch("/users/1");
            
            assertThat(response)
                .isOk()
                .hasField("firstName", "John");
        }
    }
    
    @Nested
    @DisplayName("DELETE /users/{id}")
    class DeleteUser {
        
        @Test
        @DisplayName("Should delete existing user")
        void shouldDeleteUser() {
            // First create a user to delete
            UserDTO newUser = aUserDTO()
                .withUsername("to_delete")
                .build();
            
            Response createResponse = given()
                .body(newUser)
                .post("/users");
            
            Long userId = createResponse.then().extract().path("id");
            
            // Now delete it
            Response deleteResponse = given()
                .delete("/users/" + userId);
            
            assertThat(deleteResponse)
                .isNoContent();
            
            // Verify it's gone
            Response getResponse = given()
                .get("/users/" + userId);
            
            assertThat(getResponse)
                .isNotFound();
        }
        
        @Test
        @DisplayName("Should return 404 when deleting non-existent user")
        void shouldReturn404WhenDeletingNonExistent() {
            Response response = given()
                .delete("/users/99999");
            
            assertThat(response)
                .isNotFound()
                .hasErrorContaining("not found");
        }
    }
    
    @Nested
    @DisplayName("GET /users/search")
    class SearchUsers {
        
        @Test
        @DisplayName("Should search users by query")
        void shouldSearchUsers() {
            Response response = given()
                .queryParam("q", "john")
                .get("/users/search");
            
            assertThat(response)
                .isOk()
                .isNotEmpty();
        }
        
        @Test
        @DisplayName("Should return empty list for no matches")
        void shouldReturnEmptyForNoMatches() {
            Response response = given()
                .queryParam("q", "nonexistent")
                .get("/users/search");
            
            assertThat(response)
                .isOk()
                .isEmpty();
        }
        
        @Test
        @DisplayName("Should return empty list for empty query")
        void shouldReturnEmptyForEmptyQuery() {
            Response response = given()
                .queryParam("q", "")
                .get("/users/search");
            
            assertThat(response)
                .isOk()
                .isEmpty();
        }
    }
}