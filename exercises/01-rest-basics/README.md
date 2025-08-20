# Exercise 01: REST Basics

## Learning Objectives

- Understand REST principles and constraints
- Learn HTTP methods and their proper usage
- Understand status codes and their meanings
- Design RESTful URIs

## Exercise Overview

In this exercise, you'll learn the fundamental concepts of REST and apply them
to design a simple API.

## Part 1: REST Principles (Discussion)

### The Six REST Constraints

1. **Client-Server Architecture**

   - Separation of concerns
   - UI concerns vs data storage concerns

2. **Statelessness**

   - No client context stored on server
   - Each request contains all information

3. **Cacheable**

   - Responses must define themselves as cacheable or not
   - Improves efficiency and scalability

4. **Uniform Interface**

   - Resource identification (URIs)
   - Resource manipulation through representations
   - Self-descriptive messages
   - HATEOAS (Hypermedia as the Engine of Application State)

5. **Layered System**

   - Hierarchical layers
   - Each layer doesn't "see" beyond immediate layer

6. **Code on Demand (Optional)**
   - Servers can extend client functionality

## Part 2: HTTP Methods

Match the HTTP methods with their correct usage:

| Method | Purpose | Idempotent | Safe |
| ------ | ------- | ---------- | ---- |
| GET    | ?       | ?          | ?    |
| POST   | ?       | ?          | ?    |
| PUT    | ?       | ?          | ?    |
| PATCH  | ?       | ?          | ?    |
| DELETE | ?       | ?          | ?    |

### Your Task

Fill in the table above with:

- Purpose: What the method is used for
- Idempotent: Yes/No (Can it be called multiple times with same result?)
- Safe: Yes/No (Does it modify resources?)

## Part 3: Status Codes

Categorize these common HTTP status codes:

### 2xx Success

- 200: ?
- 201: ?
- 204: ?

### 3xx Redirection

- 301: ?
- 304: ?

### 4xx Client Error

- 400: ?
- 401: ?
- 403: ?
- 404: ?
- 409: ?
- 422: ?

### 5xx Server Error

- 500: ?
- 503: ?

## Part 4: URI Design

Design RESTful URIs for a library management system with books, authors, and
members.

### Resources to Consider

- Books
- Authors
- Members
- Loans (when a member borrows a book)

### Your Task

Design URIs for these operations:

1. Get all books
2. Get a specific book
3. Get all books by a specific author
4. Create a new book
5. Update a book
6. Delete a book
7. Get all loans for a member
8. Create a new loan
9. Return a book (complete a loan)
10. Search for books by title

Example format:

```
GET /api/v1/books - Get all books
```

## Part 5: REST Anti-patterns

Identify what's wrong with these URIs and fix them:

1. `GET /getBooks`
2. `POST /books/create`
3. `GET /books/delete/123`
4. `PUT /updateBook?id=123`
5. `GET /books/author/john/get`

## Part 6: Practical Application

Using curl or Postman, test these concepts against the example API:

```bash
# Test GET
curl -X GET http://localhost:8080/api/example/users

# Test GET with ID
curl -X GET http://localhost:8080/api/example/users/1

# Test 404
curl -X GET http://localhost:8080/api/example/users/99999
```

## Submission

Create a file `answers.md` with your solutions to Parts 2-5.

## Bonus Challenge

Design a versioning strategy for the library API. Consider:

- URI versioning (/v1/, /v2/)
- Header versioning
- Query parameter versioning
- Media type versioning

Which would you choose and why?

## Resources

- [REST API Tutorial](https://restfulapi.net/)
- [HTTP Status Codes](https://httpstatuses.com/)
- [Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html)
