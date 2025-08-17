---
title: REST API Training with Jersey
author: Thomas Becker
subtitle: Module 1 - REST Fundamentals
---

## Welcome to REST API Training

**DBH REST API Training**
Day 1 - Module 1

Instructor: Thomas Becker
August 26, 2025

Target Environment: Java 8

<!-- end_slide -->

## Agenda - Day 1

### Morning (9:00 - 12:30)

- Module 1: REST Fundamentals
- Module 2: Resource Design
- Module 3: Jersey Framework Setup

### Afternoon (13:30 - 17:00)

- Module 4: CRUD Operations
- Module 5: API Versioning
- Module 6: Error Handling

<!-- end_slide -->

## What is REST?

**Representational State Transfer**

Architectural style for distributed systems

Key Principles:

- Client-Server separation
- Statelessness
- Uniform Interface
- Layered System
- Cacheability

<!-- end_slide -->

## REST vs Traditional Web Services

### SOAP/XML Web Services

- Protocol-based (SOAP)
- WSDL for description
- XML only
- Complex standards

### RESTful Services

- Architecture style
- Self-descriptive
- Multiple formats (JSON, XML)
- Simple HTTP

<!-- end_slide -->

## HTTP Methods in REST

```
GET     - Retrieve resources
POST    - Create new resources
PUT     - Update/Replace resources
PATCH   - Partial update
DELETE  - Remove resources
```

**Safe Methods**: GET, HEAD, OPTIONS
**Idempotent**: GET, PUT, DELETE

<!-- end_slide -->

## REST Resources

Everything is a **Resource**

- Identified by URIs
- Multiple representations
- Self-descriptive messages

Examples:

```
/api/users           - User collection
/api/users/123       - Specific user
/api/users/123/orders - User's orders
```

<!-- end_slide -->

## HTTP Status Codes

### Success (2xx)

- 200 OK
- 201 Created
- 204 No Content

### Client Errors (4xx)

- 400 Bad Request
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found

### Server Errors (5xx)

- 500 Internal Server Error
- 503 Service Unavailable

<!-- end_slide -->

## Richardson Maturity Model

### Level 0: The Swamp of POX

- Single URI, single HTTP method
- RPC style over HTTP

### Level 1: Resources

- Multiple URIs for different resources
- Still using single HTTP method

<!-- end_slide -->

## Richardson Maturity Model (cont.)

### Level 2: HTTP Verbs

- Correct use of HTTP methods
- Proper status codes
- Most REST APIs stop here

### Level 3: Hypermedia Controls

- HATEOAS
- Self-descriptive APIs
- Links to related resources

<!-- end_slide -->

## REST Best Practices

1. **Use Nouns, Not Verbs**
   ✅ `/users`
   ❌ `/getUsers`

2. **Use Plural Nouns**
   ✅ `/books`
   ❌ `/book`

3. **Hierarchical Resources**
   `/users/123/orders/456`

<!-- end_slide -->

## Content Negotiation

Client specifies desired format:

```http
Accept: application/json
Accept: application/xml
Accept: text/html
```

Server responds with:

```http
Content-Type: application/json
```

<!-- end_slide -->

## Real-World REST APIs

### GitHub API

```
GET /repos/{owner}/{repo}
GET /users/{username}
POST /user/repos
```

### Twitter API

```
GET /statuses/show/{id}
POST /statuses/update
DELETE /statuses/destroy/{id}
```

<!-- end_slide -->

## Exercise 1: Analyze REST APIs

**Task**: Analyze a public REST API

1. Choose: GitHub, Twitter, or Spotify
2. Identify REST principles used
3. Note Richardson Maturity Level
4. Find any anti-patterns

**Time**: 15 minutes

<!-- end_slide -->

## Common REST Anti-Patterns

### ❌ Verbs in URLs

```
/api/getUser/123
/api/deleteUser/123
```

### ❌ Ignoring HTTP Methods

```
POST /api/users/delete/123
```

### ❌ Poor Status Code Usage

- Always returning 200 OK
- Using 200 for errors

<!-- end_slide -->

## REST Security Considerations

### Authentication Methods

- Basic Authentication
- API Keys
- OAuth 2.0
- JWT Tokens

### Best Practices

- Always use HTTPS
- Validate all inputs
- Rate limiting
- Audit logging

<!-- end_slide -->

## REST vs GraphQL

### REST

- Multiple endpoints
- Fixed data structure
- Over/Under fetching
- Simple caching

### GraphQL

- Single endpoint
- Flexible queries
- Exact data needs
- Complex caching

**For this training: Focus on REST**

<!-- end_slide -->

## Summary

### Key Takeaways

✓ REST is an architectural style
✓ Resources identified by URIs
✓ HTTP methods define operations
✓ Stateless communication
✓ Multiple representation formats

### Next: Resource Design

How to model your API resources

<!-- end_slide -->

## Questions?

Ready for Module 2: Resource Design

<!-- end_slide -->
