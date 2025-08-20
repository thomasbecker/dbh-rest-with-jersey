# DBH REST Training - Base Project

This is the base project for the DBH REST API training using Jersey 2.35 (JAX-RS) 
without Spring Boot. The project demonstrates how to build REST APIs using plain 
Java with an embedded Jetty server.

## Prerequisites

- **Java 8** (JDK 1.8)
- **Gradle 7.6+** (or use the included Gradle wrapper)
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code)
- **Git**
- **curl** or Postman for testing

## Technology Stack

- **Jersey 2.35**: JAX-RS implementation for REST APIs
- **Jetty 9.4.51**: Embedded HTTP server
- **Jackson 2.14.3**: JSON processing
- **SLF4J + Logback**: Logging
- **JUnit 5**: Testing framework
- **REST Assured**: API testing

## Project Structure

```
base-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/dbh/training/rest/
│   │   │       ├── Application.java         # Main entry point
│   │   │       ├── config/
│   │   │       │   ├── JerseyConfig.java   # Jersey configuration
│   │   │       │   └── JacksonConfig.java  # JSON configuration
│   │   │       ├── resources/              # REST endpoints
│   │   │       │   └── HealthResource.java # Health check endpoint
│   │   │       ├── models/                 # Domain models
│   │   │       ├── dto/                    # Data Transfer Objects
│   │   │       ├── exceptions/             # Custom exceptions
│   │   │       ├── filters/                # HTTP filters
│   │   │       └── mappers/                # Exception mappers
│   │   └── resources/
│   │       ├── application.properties      # Configuration
│   │       └── logback.xml                # Logging configuration
│   └── test/
│       └── java/                          # Test classes
├── build.gradle                           # Build configuration
└── README.md                             # This file
```

## Quick Start

### 1. Clone or Copy the Project

```bash
cd base-project
```

### 2. Build the Project

Using Gradle wrapper (recommended):
```bash
./gradlew build
```

Or if you have Gradle installed:
```bash
gradle build
```

### 3. Run the Application

```bash
./gradlew run
```

The server will start on port 8080. You should see:
```
Server running at http://localhost:8080
API endpoint: http://localhost:8080/api
Health check: http://localhost:8080/api/health
```

### 4. Test the Health Endpoint

```bash
curl http://localhost:8080/api/health
```

Expected response:
```json
{
  "status": "UP",
  "timestamp": "2024-08-20T10:30:45.123",
  "service": "DBH REST Training API",
  "version": "1.0.0"
}
```

Detailed health check:
```bash
curl http://localhost:8080/api/health/details
```

## Available Gradle Tasks

```bash
# Display available tasks
./gradlew tasks

# Build the project
./gradlew build

# Run the application
./gradlew run

# Run tests
./gradlew test

# Clean build artifacts
./gradlew clean

# Create a fat JAR with all dependencies
./gradlew fatJar

# Display project information
./gradlew info
```

## Running with Different Ports

### Using Command Line Argument
```bash
java -jar build/libs/dbh-rest-training-1.0.0-all.jar 9090
```

### Using Environment Variable
```bash
export PORT=9090
./gradlew run
```

## IDE Setup

### IntelliJ IDEA

1. Open IntelliJ IDEA
2. Select "Open" and navigate to the base-project folder
3. IntelliJ should automatically detect it as a Gradle project
4. Wait for indexing to complete
5. Run the Application class directly from the IDE

### Eclipse

1. Install the Gradle plugin if not already installed
2. Import as "Existing Gradle Project"
3. Select the base-project folder
4. Finish the import wizard
5. Run Application.java as Java Application

### VS Code

1. Install Java Extension Pack
2. Open the base-project folder
3. VS Code should recognize it as a Java project
4. Run using the Run/Debug buttons in Application.java

## Testing the API

### Using curl

```bash
# GET request
curl -X GET http://localhost:8080/api/health

# GET with headers
curl -X GET http://localhost:8080/api/health \
  -H "Accept: application/json"

# POST request (when you implement endpoints)
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"username":"john","email":"john@example.com"}'
```

### Using Postman

1. Import the following base URL: `http://localhost:8080/api`
2. Create requests for different endpoints
3. Set appropriate headers (Content-Type, Accept)
4. Send requests and examine responses

## Configuration

Configuration can be modified in `src/main/resources/application.properties`:

- `server.port`: Change the default port (8080)
- `logging.level.*`: Adjust logging levels
- `cors.*`: Configure CORS settings
- `json.*`: Jackson JSON settings

## Logging

Logs are written to:
- Console (with colored output)
- `logs/application.log` file (with daily rotation)

Adjust logging levels in `src/main/resources/logback.xml`

## Common Issues and Solutions

### Port Already in Use

**Error**: `Address already in use`

**Solution**: 
- Change the port: `./gradlew run -Dserver.port=9090`
- Or kill the process using port 8080:
  ```bash
  # Find process
  lsof -i :8080
  # Kill it
  kill -9 <PID>
  ```

### Java Version Issues

**Error**: `Unsupported class file major version`

**Solution**: Ensure you're using Java 8:
```bash
java -version
# Should show 1.8.x
```

### Gradle Build Fails

**Solution**: 
- Clear Gradle cache: `./gradlew clean build --refresh-dependencies`
- Use the Gradle wrapper instead of system Gradle

### IDE Not Recognizing Project

**Solution**:
- Refresh/reimport the Gradle project
- Invalidate caches and restart (IntelliJ)
- Ensure Gradle plugin is installed

## Features Included

✅ Embedded Jetty server  
✅ Jersey REST framework  
✅ Jackson JSON processing  
✅ CORS filter for cross-origin requests  
✅ Request/Response logging  
✅ Global exception handling  
✅ Health check endpoint  
✅ Structured logging with SLF4J/Logback  
✅ Configuration via properties file  
✅ Fat JAR generation  
✅ Test infrastructure setup  

## Next Steps

This base project is ready for:

1. **Adding Domain Models**: Create your entity classes in the `models` package
2. **Implementing Resources**: Add REST endpoints in the `resources` package
3. **Adding Services**: Create business logic in a new `services` package
4. **Security**: Implement authentication/authorization filters
5. **Database**: Add JPA/Hibernate or JDBC for persistence
6. **Testing**: Write integration tests using REST Assured

## Support

For issues or questions during the training:
1. Check this README first
2. Review the logs for error details
3. Ask your instructor
4. Check the Jersey documentation: https://eclipse-ee4j.github.io/jersey/

## License

This project is for training purposes only.