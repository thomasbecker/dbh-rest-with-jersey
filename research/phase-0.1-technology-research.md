# Phase 0.1: Technology Research Report

## Executive Summary

This document summarizes comprehensive research on Jersey, Jackson, and JWT libraries
for Java 8 compatibility, conducted for the DBH REST API training preparation.

## 1. Jersey Framework (Java 8 Compatible)

### Version Selection

- **Recommended**: Jersey 2.35 or 2.39
- **Critical**: Must use Jersey 2.x branch (not 3.x which requires Java 11+)
- **Latest Java 8 compatible**: Jersey 2.39.1 (released 2023)

### Key Features for Training

1. **JAX-RS 2.1 Implementation**

   - Full REST API support
   - Annotations-based resource mapping
   - Content negotiation

2. **Embedded Server Support**

   - Jetty integration (preferred for training)
   - Grizzly as alternative
   - Simple main() class deployment

3. **Jersey-specific Extensions**
   - Client API for testing
   - JSON support via Jackson integration
   - Filter and interceptor chains

### Best Practices (Java 8)

```java
// Resource class example
@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    // Implementation
}
```

### Integration with Embedded Jetty

```xml
<dependency>
    <groupId>org.glassfish.jersey.containers</groupId>
    <artifactId>jersey-container-jetty-http</artifactId>
    <version>2.35</version>
</dependency>
```

## 2. Jackson Library (Java 8 Compatible)

### Version Selection

- **Recommended**: Jackson 2.14.x or 2.15.x
- **Latest Java 8 compatible**: Jackson 2.15.3
- **Core modules**: jackson-databind, jackson-core, jackson-annotations

### Key Training Topics

#### Basic Features

1. **Object Mapping**

   ```java
   ObjectMapper mapper = new ObjectMapper(); // create once, reuse
   MyValue value = mapper.readValue(json, MyValue.class);
   String json = mapper.writeValueAsString(value);
   ```

2. **Annotations**

   - `@JsonProperty` - field naming
   - `@JsonIgnore` - exclusion
   - `@JsonIgnoreProperties` - class-level ignore
   - `@JsonCreator` - custom constructors

3. **Configuration**

   ```java
   mapper.enable(SerializationFeature.INDENT_OUTPUT);
   mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
   ```

#### Advanced Features

1. **Custom Serializers/Deserializers**
2. **Polymorphic type handling**
3. **JSON Views**
4. **Builder pattern support with `@JsonPOJOBuilder`**
5. **Tree Model for dynamic JSON**

### Jersey Integration

```xml
<dependency>
    <groupId>org.glassfish.jersey.media</groupId>
    <artifactId>jersey-media-json-jackson</artifactId>
    <version>2.35</version>
</dependency>
```

## 3. JWT Libraries Analysis

### Library Comparison Matrix

| Library             | Java 8 Support  | Pros                                                  | Cons                                 | Recommendation           |
| ------------------- | --------------- | ----------------------------------------------------- | ------------------------------------ | ------------------------ |
| **jjwt**            | ✅ Yes (0.11.5) | - Simple API<br>- Good docs<br>- Lightweight          | - Less features<br>- No JWKS support | ✅ **Best for training** |
| **java-jwt**        | ✅ Yes (4.4.0)  | - Auth0 backed<br>- Simple<br>- Well maintained       | - Basic features only                | Good alternative         |
| **nimbus-jose-jwt** | ✅ Yes (9.x)    | - Full JOSE suite<br>- JWKS support<br>- Feature rich | - Complex API<br>- Heavier           | For advanced scenarios   |

### Recommended: JJWT for Training

#### Maven Dependency

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
```

#### Basic Usage Pattern

```java
// Creating JWT
String jwt = Jwts.builder()
    .setSubject(username)
    .setExpiration(expiryDate)
    .signWith(SignatureAlgorithm.HS512, secret)
    .compact();

// Parsing JWT
Claims claims = Jwts.parser()
    .setSigningKey(secret)
    .parseClaimsJws(jwt)
    .getBody();
```

## 4. REST API Versioning Strategies

### Approaches Comparison

1. **URI Path Versioning** (Recommended for training)

   ```
   /api/v1/users
   /api/v2/users
   ```

   - ✅ Clear and visible
   - ✅ Easy to route
   - ❌ URL proliferation

2. **Header Versioning**

   ```
   Accept: application/vnd.api+json;version=2
   X-API-Version: 2
   ```

   - ✅ Clean URLs
   - ✅ REST purist approach
   - ❌ Less discoverable

3. **Query Parameter**
   ```
   /api/users?version=2
   ```
   - ✅ Simple to implement
   - ❌ Can be overlooked

### Jersey Implementation

```java
// URI versioning
@Path("/v1/users")
public class UserResourceV1 { }

@Path("/v2/users")
public class UserResourceV2 { }

// Header versioning
@GET
@Produces("application/vnd.api-v1+json")
public Response getUsersV1() { }
```

## 5. Embedded Server Configuration

### Jetty vs Grizzly Comparison

| Aspect             | Jetty               | Grizzly     |
| ------------------ | ------------------- | ----------- |
| Popularity         | Higher              | Lower       |
| Documentation      | Extensive           | Good        |
| Performance        | Excellent           | Excellent   |
| Jersey Integration | Native              | Native      |
| **Recommendation** | ✅ Use for training | Alternative |

### Basic Jetty Setup

```java
public class Application {
    public static void main(String[] args) {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        server.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
            org.glassfish.jersey.servlet.ServletContainer.class, "/api/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter(
            "jersey.config.server.provider.packages",
            "com.dbh.training.resources");

        server.start();
        server.join();
    }
}
```

## 6. Security Implementation Patterns

### Authentication Approaches

1. **Basic Authentication**

   - Simple for exercises
   - Base64 encoding
   - Stateless

2. **JWT Tokens** (Focus area)

   - Stateless
   - Scalable
   - Industry standard

3. **API Keys**
   - Simple implementation
   - Good for service-to-service

### Jersey Security Filter

```java
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        // Validation logic
    }
}
```

## 7. Testing Strategy

### REST Assured Configuration

```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.3.0</version>
    <scope>test</scope>
</dependency>
```

### Integration Test Pattern

```java
@Test
public void testGetUser() {
    given()
        .port(8080)
        .when()
        .get("/api/users/1")
        .then()
        .statusCode(200)
        .body("name", equalTo("John"));
}
```

## 8. Enterprise Patterns

### Richardson Maturity Model

- **Level 0**: Single URI, single HTTP method
- **Level 1**: Multiple URIs, single HTTP method
- **Level 2**: HTTP verbs (GET, POST, PUT, DELETE)
- **Level 3**: HATEOAS (Hypermedia controls)

### Error Handling

```java
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        ErrorResponse error = new ErrorResponse(
            500,
            exception.getMessage(),
            System.currentTimeMillis()
        );
        return Response.status(500).entity(error).build();
    }
}
```

## 9. Training Exercise Progression

### Suggested Order

1. Basic Jersey resource creation
2. CRUD operations
3. Jackson integration
4. Exception handling
5. Validation
6. Versioning
7. Security filters
8. JWT implementation
9. Advanced Jackson features
10. Complete API project

## 10. Common Pitfalls (Java 8 Specific)

1. **Date/Time Handling**
   - Use Java 8 time API
   - Configure Jackson JavaTimeModule
2. **Lambda Limitations**
   - No var keyword
   - Type inference limitations
3. **Module System**

   - Not available (Java 9+)
   - Use traditional classpath

4. **HTTP Client**
   - No built-in HttpClient (Java 11+)
   - Use Jersey Client or Apache HttpClient

## Recommendations Summary

### Core Stack

- **Jersey**: 2.35 or 2.39
- **Jackson**: 2.14.x or 2.15.x
- **JWT**: JJWT 0.11.5
- **Embedded Server**: Jetty 9.x
- **Testing**: REST Assured 5.3.0
- **Build Tool**: Gradle 8.5+

### Architecture Decisions

1. Use embedded Jetty for simplicity
2. URI versioning for clarity
3. JJWT for JWT implementation
4. Jackson for all JSON processing
5. Jersey filters for cross-cutting concerns

### Training Focus Areas

1. REST principles and maturity models
2. Jersey annotations and resource classes
3. Jackson annotations and custom serialization
4. JWT token lifecycle
5. Security best practices
6. Testing strategies

## Resources and References

### Documentation

- [Jersey User Guide](https://eclipse-ee4j.github.io/jersey.github.io/documentation/latest/user-guide.html)
- [Jackson Documentation](https://github.com/FasterXML/jackson-docs)
- [JJWT Documentation](https://github.com/jwtk/jjwt)

### Example Projects

- [Jersey with Embedded Jetty](https://github.com/edomingues/jersey2-jetty-example)
- [Jersey REST Examples](https://github.com/eclipse-ee4j/jersey/tree/2.x/examples)

### Articles

- REST API Design Best Practices
- Jersey vs Spring Boot Comparison
- JWT Security Considerations

---

**Document Version**: 1.0
**Date**: December 2024
**Author**: Training Development Team
**Java Version**: 8
**Training Date**: August 26, 2025
