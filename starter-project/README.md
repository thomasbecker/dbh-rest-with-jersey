# Jersey REST API Starter Project

This is your starting point for the DBH REST with Jersey training exercises.

## What's Included

### ✅ Infrastructure (Already Set Up)
- **Embedded Jetty Server** - Ready to run on port 8080
- **Jersey Configuration** - JAX-RS implementation configured
- **Jackson Integration** - Basic JSON serialization set up
- **Logging** - SLF4J with Logback configured
- **CORS Filter** - Cross-origin requests enabled
- **Request Logging Filter** - Logs all incoming requests
- **Gradle Build** - All dependencies configured for Java 8

### 📝 Your Tasks (To Be Implemented)

You will progressively build the following during the exercises:

1. **Domain Models** - User, Product, Order with fields and validation
2. **DTOs** - Data Transfer Objects for clean API responses
3. **REST Resources** - Implement CRUD operations
4. **Exception Handling** - Proper error responses
5. **Security** - Authentication and authorization

## Getting Started

### Prerequisites
- Java 8
- Gradle 8.5+ (or use the wrapper)
- Your favorite IDE

### Running the Application

```bash
# Build the project
gradle build

# Run the application
gradle run

# Or run directly
java -jar build/libs/base-project-1.0.0.jar
```

The server will start on http://localhost:8080

### Testing the Server

```bash
# Health check endpoint (already implemented)
curl http://localhost:8080/api/health

# You should see:
# {"status":"UP","timestamp":"2025-01-20T10:00:00"}
```

## Exercise Structure

Each exercise will guide you through implementing specific features:

- **Exercise 01**: REST Principles Analysis (theory)
- **Exercise 02**: Resource Design (planning)
- **Exercise 03**: Implement your first resource
- **Exercise 04**: Full CRUD operations
- **Exercise 04b**: Add validation
- **Exercise 05**: API versioning
- **Exercise 06**: Jackson configuration
- **Exercise 07**: Advanced Jackson features
- **Exercise 08-10**: Security implementation

## Project Structure

```
src/main/java/com/dbh/training/rest/
├── Application.java           # Main class (✅ provided)
├── config/
│   └── JerseyConfig.java     # Jersey setup (✅ provided)
├── models/                   # Domain models (📝 you implement)
│   ├── User.java             # Stub with TODOs
│   ├── Product.java          # Stub with TODOs
│   └── Order.java            # Stub with TODOs
├── dto/                      # DTOs (📝 you create)
├── resources/                # REST endpoints (📝 you create)
├── filters/                  # HTTP filters (✅ provided)
│   ├── CORSFilter.java
│   └── LoggingFilter.java
├── exceptions/               # Exception handling (📝 you enhance)
└── util/                     # Utilities (📝 you create)
```

## Tips

1. **Start Simple** - Get basic functionality working before adding complexity
2. **Test Often** - Use curl or Postman to test your endpoints
3. **Check Logs** - The console shows detailed request/response information
4. **Ask Questions** - If stuck, refer to the instructor solution or ask!

## Useful Commands

```bash
# Watch the logs
tail -f logs/server.log

# Test with curl (examples)
curl -X GET http://localhost:8080/api/users
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d '{...}'
curl -X PUT http://localhost:8080/api/users/1 -H "Content-Type: application/json" -d '{...}'
curl -X DELETE http://localhost:8080/api/users/1
```

## Common Issues

- **Port 8080 in use**: Change port with `-Dserver.port=8081`
- **Gradle not found**: Use `./gradlew` instead of `gradle`
- **Java version**: Ensure Java 8 is active (`java -version`)

Happy coding! 🚀