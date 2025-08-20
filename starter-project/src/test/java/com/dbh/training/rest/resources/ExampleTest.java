package com.dbh.training.rest.resources;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.*;

import com.dbh.training.rest.Application;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Example test class to help you get started with testing.
 * 
 * This shows the basic structure for integration tests:
 * 1. Start the server before tests
 * 2. Configure RestAssured
 * 3. Write tests using RestAssured DSL
 * 4. Stop the server after tests
 * 
 * TODO: Exercise 10 - Create comprehensive tests for your resources
 */
@DisplayName("Example Integration Test")
public class ExampleTest {
    
    private static Server server;
    private static final int TEST_PORT = 8081;
    
    @BeforeAll
    public static void startServer() throws Exception {
        // Start the embedded server for testing
        server = Application.createServer(TEST_PORT);
        server.start();
        
        // Configure RestAssured
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = TEST_PORT;
        RestAssured.basePath = "/api";
    }
    
    @AfterAll
    public static void stopServer() throws Exception {
        // Clean up after tests
        if (server != null) {
            server.stop();
            server.destroy();
        }
        
        RestAssured.reset();
    }
    
    @Test
    @DisplayName("Should get all users from example endpoint")
    void shouldGetAllUsers() {
        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/example/users")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", greaterThan(0));
    }
    
    @Test
    @DisplayName("Should get single user by ID")
    void shouldGetUserById() {
        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/example/users/1")
            .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("username", notNullValue());
    }
    
    @Test
    @DisplayName("Should return 404 for non-existent user")
    void shouldReturn404ForNonExistentUser() {
        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/example/users/99999")
            .then()
            .statusCode(404);
    }
    
    // TODO: Exercise 10 - Add more test scenarios:
    // - Test POST to create users
    // - Test PUT to update users
    // - Test DELETE to remove users
    // - Test validation errors
    // - Test pagination
    // - Test filtering
}