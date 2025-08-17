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

<!-- speaker_notes -->
Welcome everyone to the REST API training.
- Introduce yourself
- Ask about participants' experience with REST
- Confirm everyone has Java 8 environment ready
- Mention this is Jersey-focused, not Spring Boot
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
- We'll start with theory this morning
- Hands-on exercises increase after lunch
- Jersey setup before lunch break
- Encourage questions throughout
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
REST is not a protocol or standard, it's an architectural style.
Created by Roy Fielding in 2000 (his PhD dissertation).
Emphasize that REST is about constraints that lead to desirable properties.
Ask: Who has worked with SOAP? This will help with comparisons.
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
Many participants might have SOAP background.
Emphasize simplicity of REST.
No need for special tooling - curl is enough.
JSON has won over XML in most REST APIs.
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
Safe = doesn't change server state
Idempotent = same result if called multiple times
POST is neither safe nor idempotent
PATCH is relatively new, not all APIs support it
PUT should replace entire resource
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
Resources are the key abstraction in REST.
Think of resources as nouns, not actions.
A resource can have multiple representations (JSON, XML, HTML).
URIs should be intuitive and hierarchical.
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
Don't just use 200 for everything!
201 should include Location header
204 for successful DELETE
401 vs 403: Authentication vs Authorization
Never expose stack traces in 500 errors
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
POX = Plain Old XML (or JSON)
This is basically RPC over HTTP
Many legacy "REST" APIs are actually Level 0
<!-- end_speaker_notes -->

---

# Richardson Maturity Model

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

<!-- speaker_notes -->
Most REST APIs reach Level 2 and stop
Level 2 is considered "good enough" for most cases
This is what we'll focus on in this training
<!-- end_speaker_notes -->

---

# Richardson Maturity Model

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

<!-- speaker_notes -->
HATEOAS = Hypermedia as the Engine of Application State
Clients discover available actions through links
Very few APIs actually implement this
Adds complexity that may not be needed
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
These are conventions, not rules
Consistency is more important than the specific choice
Some argue for singular nouns - pick one and stick with it
Hierarchical URIs show relationships
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
Accept header tells server what client wants
Content-Type tells client what server is sending
Can support multiple formats with same endpoint
Quality values (q=0.9) for preference ordering
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
These are real examples from production APIs
Notice the patterns - plural nouns, clear hierarchy
GitHub uses hypermedia (Level 3)
Twitter is mostly Level 2
Both are successful - pragmatism wins
<!-- end_speaker_notes -->

---

# 🏃 Exercise 1: Analyze REST APIs

## **Task**: Analyze a public REST API

1. Choose: GitHub, Twitter, or Spotify
2. Identify REST principles used
3. Note Richardson Maturity Level
4. Find any anti-patterns

**Time**: 15 minutes

<!-- speaker_notes -->
Split into groups of 2-3
Use browser or curl to explore
Look at documentation first
GitHub: api.github.com
Twitter: developer.twitter.com
Spotify: developer.spotify.com
Walk around and help groups
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
These are very common in enterprise APIs
Often result from RPC mindset
Or from developers not understanding HTTP
We'll avoid these in our Jersey implementation
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
We'll implement JWT in Day 2
Basic Auth is simple but credentials sent every request
API Keys good for service-to-service
OAuth complex but powerful for user delegation
Never trust client input
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
GraphQL solves some REST problems
But adds complexity
REST is still dominant in enterprise
Jersey doesn't support GraphQL
Good to know the trade-offs
<!-- end_speaker_notes -->

---

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

<!-- speaker_notes -->
Quick recap of main points
Check if any questions before moving on
Resource design is critical for good APIs
We'll design a complete e-commerce API
Take 5 minute break before Module 2
<!-- end_speaker_notes -->

---

# Questions?

## Ready for Module 2: Resource Design

<!-- speaker_notes -->
Address any remaining questions
If complex questions, note them for later
Mention we'll see practical implementation after lunch
Encourage participants to think about their own APIs
<!-- end_speaker_notes -->