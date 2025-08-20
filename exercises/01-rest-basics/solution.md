# Exercise 01: REST Basics - Solution

## Part 2: HTTP Methods

| Method | Purpose | Idempotent | Safe |
|--------|---------|------------|------|
| GET    | Retrieve resource(s) | Yes | Yes |
| POST   | Create new resource | No | No |
| PUT    | Update/Replace entire resource | Yes | No |
| PATCH  | Partial update of resource | No* | No |
| DELETE | Remove resource | Yes | No |

*PATCH can be idempotent depending on implementation

## Part 3: Status Codes

### 2xx Success
- 200: OK - Request succeeded
- 201: Created - New resource created
- 204: No Content - Success but no response body

### 3xx Redirection
- 301: Moved Permanently - Resource moved to new URI
- 304: Not Modified - Cached version is still valid

### 4xx Client Error
- 400: Bad Request - Invalid request syntax
- 401: Unauthorized - Authentication required
- 403: Forbidden - Authenticated but not authorized
- 404: Not Found - Resource doesn't exist
- 409: Conflict - Request conflicts with current state
- 422: Unprocessable Entity - Valid syntax but semantic errors

### 5xx Server Error
- 500: Internal Server Error - Generic server error
- 503: Service Unavailable - Server temporarily unavailable

## Part 4: URI Design

```
1. GET    /api/v1/books                     - Get all books
2. GET    /api/v1/books/{id}                - Get a specific book
3. GET    /api/v1/authors/{id}/books        - Get all books by author
4. POST   /api/v1/books                     - Create a new book
5. PUT    /api/v1/books/{id}                - Update a book
6. DELETE /api/v1/books/{id}                - Delete a book
7. GET    /api/v1/members/{id}/loans        - Get all loans for a member
8. POST   /api/v1/loans                     - Create a new loan
9. PUT    /api/v1/loans/{id}/return         - Return a book
10. GET   /api/v1/books?search={title}      - Search books by title
```

Alternative for #9:
```
PATCH /api/v1/loans/{id}
{
  "status": "RETURNED",
  "returnDate": "2024-01-20"
}
```

## Part 5: REST Anti-patterns - Fixed

1. ❌ `GET /getBooks`
   ✅ `GET /books` (verb in URI is redundant)

2. ❌ `POST /books/create`
   ✅ `POST /books` (verb in URI is redundant)

3. ❌ `GET /books/delete/123`
   ✅ `DELETE /books/123` (wrong HTTP method)

4. ❌ `PUT /updateBook?id=123`
   ✅ `PUT /books/123` (ID should be in path, no verb)

5. ❌ `GET /books/author/john/get`
   ✅ `GET /authors/john/books` (cleaner hierarchy, no verb)

## Common Mistakes Explained

1. **Using verbs in URIs**: The HTTP method IS the verb
2. **Using GET for state changes**: GET must be safe
3. **Query params for resource identification**: Use path params
4. **Inconsistent pluralization**: Pick plural or singular and stick with it
5. **Deep nesting**: Avoid URIs like `/users/1/posts/2/comments/3/likes/4`

## Bonus Challenge: Versioning Strategy

### Recommended: URI Versioning
```
/api/v1/books
/api/v2/books
```

**Pros:**
- Clear and visible
- Easy to route
- Cache-friendly
- Simple for clients

**Cons:**
- URIs change between versions
- Can lead to code duplication

### Alternative: Header Versioning
```
GET /api/books
API-Version: 1.0
```

**Pros:**
- Clean URIs
- More RESTful (arguably)

**Cons:**
- Less discoverable
- Harder to test with browser
- Proxy/cache complications

### Decision Factors
- **Choose URI versioning when:**
  - You need clear, simple versioning
  - Your API is public
  - You want easy testing
  
- **Choose header versioning when:**
  - You want stable URIs
  - You have sophisticated clients
  - You can control the full stack

## Best Practices Summary

1. **Use nouns for resources, not verbs**
2. **Use plural nouns consistently**
3. **Use HTTP methods correctly**
4. **Return appropriate status codes**
5. **Keep URIs shallow when possible**
6. **Use query parameters for filtering/searching**
7. **Use path parameters for resource identification**
8. **Version your API from day one**
9. **Be consistent across your entire API**
10. **Document everything clearly**