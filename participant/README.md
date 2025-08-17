# DBH REST API Training with Jersey

## Welcome Participants!

This repository contains all the materials you need for the REST API training
with Jersey and Jackson.

## Prerequisites

- Java Development Kit 8
- IntelliJ IDEA or comparable IDE
- Gradle 8.5+
- REST Client (Postman, Insomnia, or similar)
- curl CLI
- Git

## Repository Structure

```
participant/
├── base-project/     # Your starting template with Jersey configured
├── exercises/        # Progressive hands-on exercises
│   ├── 01-rest-basics/
│   ├── 02-resource-design/
│   ├── 03-jersey-crud/
│   ├── 04-versioning/
│   ├── 05-jackson-basics/
│   ├── 06-jackson-advanced/
│   ├── 07-security-basic/
│   ├── 08-security-jwt/
│   ├── 09-security-apikey/
│   └── 10-final-project/
└── README.md        # This file
```

## Getting Started

1. **Clone this repository:**
   ```bash
   git clone <repository-url>
   cd dbh-jersey-training-participant
   ```

2. **Open the base-project in your IDE:**
   ```bash
   cd base-project
   ./gradlew build
   ```

3. **Verify your setup:**
   ```bash
   ./gradlew test
   java -jar build/libs/rest-training-1.0.jar
   ```

   You should see the server start on port 8080.

4. **Test with curl:**
   ```bash
   curl http://localhost:8080/api/health
   ```

## Training Schedule

### Day 1: REST Fundamentals & Jersey
- Module 1: REST Fundamentals
- Module 2: Resource Design
- Module 3: Jersey CRUD Operations
- Module 4: API Versioning

### Day 2: Jackson & Security
- Module 5: Jackson Basics
- Module 6: Jackson Advanced
- Module 7-9: Security Implementations
- Module 10: Final Project

## Exercise Workflow

Each exercise folder contains:
- `README.md` - Exercise instructions
- `starter/` - Your starting code (if applicable)
- `tests/` - Tests to validate your solution

Work through exercises sequentially as they build upon each other.

## Need Help?

- Check the exercise README for detailed instructions
- Review the base-project for examples
- Ask your instructor during the training

## Additional Resources

- [Jersey Documentation](https://eclipse-ee4j.github.io/jersey/)
- [Jackson Documentation](https://github.com/FasterXML/jackson)
- [REST API Design Best Practices](https://restfulapi.net/)

---

**Happy Coding!**