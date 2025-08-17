---
title: REST API Training with Jersey
author: Thomas Becker
date: August 26, 2025
---

# Welcome to REST API Training

**DBH REST API Training**  
Day 1 - Module 1

Instructor: Thomas Becker  
Target Environment: **Java 8**

<!--
speaker_note: |
  Welcome everyone. Introduce yourself and ask about REST experience.
  Confirm Java 8 setup. Emphasize Jersey focus, not Spring Boot.
-->

<!-- end_slide -->

# Agenda - Day 1

<!-- column_layout: [1, 1] -->

<!-- column: 0 -->

### Morning (9:00 - 12:30)

- Module 1: REST Fundamentals
- Module 2: Resource Design
- Module 3: Jersey Framework Setup

<!-- column: 1 -->

### Afternoon (13:30 - 17:00)

- Module 4: CRUD Operations
- Module 5: API Versioning
- Module 6: Error Handling

<!-- reset_layout -->

<!--
speaker_note: |
  - Theory in the morning, hands-on after lunch
  - Jersey setup before lunch break
  - Encourage questions throughout
  - Adjust pace based on group
-->

<!-- end_slide -->

# What is REST?

## **Representational State Transfer**

Architectural style for distributed systems

<!-- pause -->

### Key Principles:

- Client-Server separation
- Statelessness
- Uniform Interface
- Layered System
- Cacheability

<!--
speaker_note: |
  REST is not a protocol, it's an architectural style.
  Created by Roy Fielding in 2000 (PhD dissertation).
  Emphasize constraints that lead to desirable properties.
  Ask: Who has worked with SOAP? Helps with comparisons.
-->

<!-- end_slide -->

# REST vs Traditional Web Services

<!-- column_layout: [1, 1] -->

<!-- column: 0 -->

### SOAP/XML Web Services

- Protocol-based (SOAP)
- WSDL for description
- XML only
- Complex standards
- Heavy tooling

<!-- column: 1 -->

### RESTful Services

- Architecture style
- Self-descriptive
- Multiple formats (JSON, XML)
- Simple HTTP
- Lightweight

<!-- reset_layout -->

<!--
speaker_note: |
  Many have SOAP background. Emphasize REST simplicity.
  No special tooling needed - curl is enough.
  JSON has won over XML.
-->

<!-- end_slide -->

# HTTP Methods in REST

```http +line_numbers
GET     - Retrieve resources
POST    - Create new resources
PUT     - Update/Replace resources
PATCH   - Partial update
DELETE  - Remove resources
```

<!-- pause -->

### Important Properties:

- **Safe Methods**: GET, HEAD, OPTIONS
- **Idempotent**: GET, PUT, DELETE

<!--
speaker_note: |
  Safe = doesn't change server state
  Idempotent = same result if called multiple times
  POST is neither safe nor idempotent
  PATCH is relatively new, not all APIs support it
  PUT should replace entire resource
-->

<!-- end_slide -->

# REST Resources

## Everything is a **Resource**

- Identified by URIs
- Multiple representations
- Self-descriptive messages

<!-- pause -->

### Examples:

```http
/api/users           # User collection
/api/users/123       # Specific user
/api/users/123/orders # User's orders
```

<!--
speaker_note: |
  Resources are key abstraction. Think nouns, not actions.
  Can have multiple representations (JSON, XML, HTML).
  URIs should be intuitive and hierarchical.
-->

<!-- end_slide -->

# HTTP Status Codes

<!-- column_layout: [1, 1, 1] -->

<!-- column: 0 -->

### Success (2xx)

- 200 OK
- 201 Created
- 204 No Content

<!-- column: 1 -->

### Client Errors (4xx)

- 400 Bad Request
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found

<!-- column: 2 -->

### Server Errors (5xx)

- 500 Internal Server Error
- 502 Bad Gateway
- 503 Service Unavailable

<!-- reset_layout -->

<!--
speaker_note: |
  Don't just use 200 for everything!
  201 should include Location header
  204 for successful DELETE
  401 vs 403: Authentication vs Authorization
  Never expose stack traces in 500 errors
-->

<!-- end_slide -->

# Richardson Maturity Model

## Level 0: The Swamp of POX

- Single URI, single HTTP method
- RPC style over HTTP

```http
POST /api/endpoint
{
  "method": "getUser",
  "id": 123
}
```

<!--
speaker_note: |
  POX = Plain Old XML/JSON. This is RPC over HTTP.
  Many legacy "REST" APIs are Level 0.
-->

<!-- end_slide -->

# Richardson Maturity Model (cont.)

## Level 1: Resources

- Multiple URIs for different resources
- Still using single HTTP method

```http
POST /api/users/123
POST /api/orders/456
```

<!-- pause -->

## Level 2: HTTP Verbs

- Correct use of HTTP methods
- Proper status codes

```http
GET /api/users/123
DELETE /api/orders/456
```

<!--
speaker_note: |
  Most REST APIs reach Level 2 and stop.
  Level 2 is "good enough" for most cases.
  This is our training focus.
-->

<!-- end_slide -->

# Richardson Maturity Model - Level 3

## Level 3: Hypermedia Controls (HATEOAS)

```json {1-8|9-16|all} +line_numbers
{
  "id": 123,
  "name": "John Doe",
  "email": "john@example.com",
  "_links": {
    "self": "/api/users/123",
    "orders": "/api/users/123/orders",
    "update": {
      "href": "/api/users/123",
      "method": "PUT"
    },
    "delete": {
      "href": "/api/users/123",
      "method": "DELETE"
    }
  }
}
```

<!--
speaker_note: |
  HATEOAS = Hypermedia as Engine of Application State
  Clients discover actions through links
  Very few APIs actually implement this
  Adds complexity that may not be needed
-->

<!-- end_slide -->

# REST Best Practices

## 1. **Use Nouns, Not Verbs**

✅ `/users`  
❌ `/getUsers`

<!-- pause -->

## 2. **Use Plural Nouns**

✅ `/books`  
❌ `/book`

<!-- pause -->

## 3. **Hierarchical Resources**

✅ `/users/123/orders/456`

<!--
speaker_note: |
  Conventions, not rules. Consistency matters most.
  Some prefer singular nouns - pick one and stick with it.
  Hierarchical URIs show relationships.
-->

<!-- end_slide -->

# Content Negotiation

## Client Request:

```http +line_numbers
GET /api/users/123 HTTP/1.1
Host: api.example.com
Accept: application/json
```

<!-- pause -->

## Server Response:

```http +line_numbers
HTTP/1.1 200 OK
Content-Type: application/json

{
  "id": 123,
  "name": "John Doe"
}
```

<!--
speaker_note: |
  Accept header: what client wants
  Content-Type: what server sends
  Can support multiple formats per endpoint
  Quality values (q=0.9) for preference ordering
-->

<!-- end_slide -->

# Real-World REST APIs

<!-- column_layout: [1, 1] -->

<!-- column: 0 -->

### GitHub API

```http
GET /repos/{owner}/{repo}
GET /users/{username}
POST /user/repos
DELETE /repos/{owner}/{repo}
```

<!-- column: 1 -->

### Twitter API

```http
GET /statuses/show/{id}
POST /statuses/update
DELETE /statuses/destroy/{id}
GET /users/show
```

<!-- reset_layout -->

<!--
speaker_note: |
  Real production examples. Notice patterns - plural nouns, clear hierarchy.
  GitHub uses hypermedia (Level 3). Twitter is Level 2.
  Both successful - pragmatism wins.
-->

<!-- end_slide -->

# Exercise 1: Analyze REST APIs

## 🏃 **Task**: Analyze a public REST API

1. Choose: GitHub, Twitter, or Spotify
2. Identify REST principles used
3. Note Richardson Maturity Level
4. Find any anti-patterns

**Time**: 15 minutes

<!--
speaker_note: |
  Split into groups of 2-3
  Use browser or curl to explore
  Look at documentation first
  GitHub: api.github.com
  Twitter: developer.twitter.com
  Spotify: developer.spotify.com
  Walk around and help groups
-->

<!-- end_slide -->

# Common REST Anti-Patterns

## ❌ Verbs in URLs

```http
/api/getUser/123
/api/deleteUser/123
```

<!-- pause -->

## ❌ Ignoring HTTP Methods

```http
POST /api/users/delete/123
```

<!-- pause -->

## ❌ Poor Status Code Usage

- Always returning 200 OK
- Using 200 for errors with error in body

<!--
speaker_note: |
  Common in enterprise APIs. Result from RPC mindset or not understanding HTTP.
  We'll avoid these in Jersey implementation.
-->

<!-- end_slide -->

# REST Security Considerations

<!-- column_layout: [1, 1] -->

<!-- column: 0 -->

### Authentication Methods

- Basic Authentication
- API Keys
- OAuth 2.0
- **JWT Tokens** ← Our focus

<!-- column: 1 -->

### Best Practices

- Always use HTTPS
- Validate all inputs
- Rate limiting
- Audit logging
- CORS configuration

<!-- reset_layout -->

<!--
speaker_note: |
  We'll implement JWT in Day 2
  Basic Auth simple but credentials sent every request
  API Keys good for service-to-service
  OAuth complex but powerful for user delegation
  Never trust client input
-->

<!-- end_slide -->

# REST vs GraphQL

<!-- column_layout: [1, 1] -->

<!-- column: 0 -->

### REST

- Multiple endpoints
- Fixed data structure
- Over/Under fetching
- Simple caching
- Mature ecosystem

<!-- column: 1 -->

### GraphQL

- Single endpoint
- Flexible queries
- Exact data needs
- Complex caching
- Growing adoption

<!-- reset_layout -->

**For this training: Focus on REST with Jersey**

<!--
speaker_note: |
  GraphQL solves some REST problems but adds complexity.
  REST still dominant in enterprise.
  Jersey doesn't support GraphQL.
  Good to know trade-offs.
-->

<!-- end_slide -->

# Summary

## Key Takeaways

✓ REST is an architectural style  
✓ Resources identified by URIs  
✓ HTTP methods define operations  
✓ Stateless communication  
✓ Multiple representation formats

<!-- pause -->

## Next: Module 2 - Resource Design

How to model your API resources effectively

<!--
speaker_note: |
  Quick recap of main points
  Check for questions before moving on
  Resource design is critical for good APIs
  We'll design a complete e-commerce API
  Take 5 minute break before Module 2
-->

<!-- end_slide -->

# Questions?

## Ready for Module 2: Resource Design

<!--
speaker_note: |
  Address remaining questions. Note complex ones for later.
  Mention practical implementation after lunch.
  Encourage thinking about their own APIs.
-->
