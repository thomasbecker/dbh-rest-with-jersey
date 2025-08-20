package com.dbh.training.rest.resources;

import com.dbh.training.rest.dto.UserDTO;
import com.dbh.training.rest.test.BaseIntegrationTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static com.dbh.training.rest.test.RestAssertions.assertThat;
import static com.dbh.training.rest.test.TestDataBuilder.*;

/**
 * Simple integration tests for UserResource that should all pass.
 * 
 * These tests demonstrate basic CRUD operations.
 */
@DisplayName("Simple User Resource Tests")
public class SimpleUserResourceTest extends BaseIntegrationTest {
    
    @Test
    @DisplayName("Should return all users")
    void shouldReturnAllUsers() {
        Response response = given()
            .get("/users");
        
        assertThat(response)
            .isOk()
            .hasJsonContentType()
            .hasPaginationHeaders()
            .isNotEmpty();
    }
    
    @Test
    @DisplayName("Should get user by ID")
    void shouldGetUserById() {
        Response response = given()
            .get("/users/1");
        
        assertThat(response)
            .isOk()
            .hasUserId()
            .hasFieldNotNull("username")
            .hasFieldNotNull("email")
            .hasNoPassword();
    }
    
    @Test
    @DisplayName("Should create new user")
    void shouldCreateUser() {
        UserDTO newUser = aUserDTO()
            .withUsername("testuser_" + System.currentTimeMillis())
            .withEmail("test_" + System.currentTimeMillis() + "@example.com")
            .withName("Test", "User")
            .build();
        
        Response response = given()
            .body(newUser)
            .post("/users");
        
        assertThat(response)
            .isCreated()
            .hasLocationHeader()
            .hasUserId()
            .hasNoPassword();
    }
    
    @Test
    @DisplayName("Should update user")
    void shouldUpdateUser() {
        // First create a user
        UserDTO newUser = aUserDTO()
            .withUsername("updatetest_" + System.currentTimeMillis())
            .withEmail("update_" + System.currentTimeMillis() + "@example.com")
            .build();
        
        Response createResponse = given()
            .body(newUser)
            .post("/users");
        
        Integer userId = createResponse.then().extract().path("id");
        
        // Now update it
        UserDTO update = aUserDTO()
            .withUsername(newUser.getUsername())
            .withEmail("updated_" + System.currentTimeMillis() + "@example.com")
            .withName("Updated", "Name")
            .build();
        
        Response updateResponse = given()
            .body(update)
            .put("/users/" + userId);
        
        assertThat(updateResponse)
            .isOk()
            .hasField("firstName", "Updated")
            .hasField("lastName", "Name");
    }
    
    @Test
    @DisplayName("Should delete user")
    void shouldDeleteUser() {
        // First create a user
        UserDTO newUser = aUserDTO()
            .withUsername("deletetest_" + System.currentTimeMillis())
            .build();
        
        Response createResponse = given()
            .body(newUser)
            .post("/users");
        
        Integer userId = createResponse.then().extract().path("id");
        
        // Delete it
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
    @DisplayName("Should search users")
    void shouldSearchUsers() {
        Response response = given()
            .queryParam("q", "john")
            .get("/users/search");
        
        assertThat(response)
            .isOk()
            .hasJsonContentType();
    }
    
    @Test
    @DisplayName("Should paginate users")
    void shouldPaginateUsers() {
        Response response = given()
            .queryParam("page", 0)
            .queryParam("size", 2)
            .get("/users");
        
        assertThat(response)
            .isOk()
            .hasPage(0)
            .hasPageSize(2)
            .hasPaginationHeaders();
    }
    
    @Test
    @DisplayName("Should return 404 for non-existent user")
    void shouldReturn404ForNonExistentUser() {
        Response response = given()
            .get("/users/99999");
        
        assertThat(response)
            .isNotFound();
    }
    
    @Test
    @DisplayName("Should reject duplicate username")
    void shouldRejectDuplicateUsername() {
        UserDTO user = aUserDTO()
            .withUsername("john_doe")  // Existing username
            .withEmail("unique_" + System.currentTimeMillis() + "@example.com")
            .build();
        
        Response response = given()
            .body(user)
            .post("/users");
        
        assertThat(response)
            .isBadRequest();
    }
}