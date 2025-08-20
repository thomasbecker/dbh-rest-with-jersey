---
title: "Module 2: Resource Design & REST Principles"
author: Thomas Becker
date: 2025-08-26
---

# Module 2: Resource Design

## Advanced Patterns & Real-World Design

🎯 **Goal**: Master complex resource modeling

<!--
speaker_note: |
  MODULE 2 INTRODUCTION (2 minutes)
  
  • Transition from Module 1:
    - "Module 1 covered REST fundamentals"
    - "Now: advanced resource design"
    - "Real-world complexity"
  
  • Key message:
    - Beyond simple CRUD
    - Handle complex domains
    - Pragmatic patterns
  
  • What we'll cover:
    - Virtual & computed resources
    - Complex URI patterns
    - Stateless workflows
    - Real-world exercises
-->

<!-- end_slide -->

# Beyond Basic Resources 🎯

## Resources ≠ Database Tables

### Virtual & Computed Resources
- Search results → `/search?q=rest`
- Reports → `/analytics/monthly`
- Aggregations → `/statistics/user-activity`
- Processes → `/jobs/import-456`

### Cross-Domain Resources
- Dashboard views → `/dashboards/executive`
- Merged data → `/customer-360/123`
- Calculated metrics → `/kpis/revenue/ytd`

**Key**: Think conceptually, not physically!

<!--
speaker_note: |
  BEYOND BASIC RESOURCES (4 minutes)
  
  • Module 1 covered basics, now go deeper:
    - Resources can be virtual
    - Can span multiple tables
    - Can be external APIs
    - Can be computed on-demand
  
  • Virtual resources:
    - Search: dynamic generation
    - Reports: aggregated data
    - Analytics: calculated metrics
  
  • Cross-domain:
    - Customer-360: joins many tables
    - Dashboard: multiple data sources
    - KPIs: complex calculations
  
  • Ask audience:
    - "What computed resources in your domain?"
    - "Any cross-domain views needed?"
-->

<!-- end_slide -->

# Advanced URI Design Patterns

## Beyond Simple Hierarchies

### Alternate Access Paths
```http
# Primary path
GET /courses/CS101/students

# Alternate path for same data
GET /students?course=CS101

# Both valid! Choose based on primary use case
```

### Composite Resources
```http
# Combining multiple entities
GET /orders/123/invoice      # Generated from order + customer + items
GET /users/456/profile       # User + preferences + stats
```

### Filtering vs Sub-Resources
```http
# Sub-resource (strong ownership)
GET /users/123/addresses     

# Filtered collection (weak relationship)
GET /addresses?user=123      
```

<!--
speaker_note: |
  ADVANCED URI PATTERNS (5 minutes)
  
  • Module 1 covered basics, now advanced:
  
  • Alternate paths:
    - Same data, different access
    - Choose based on common use
    - OK to have both!
  
  • Composite resources:
    - Invoice: computed from multiple
    - Profile: aggregated view
    - Dashboard: cross-domain data
  
  • When to use sub-resources:
    - Strong ownership (user→addresses)
    - Cascade delete makes sense
    - Clear parent-child
  
  • When to use filtering:
    - Weak relationship
    - Independent lifecycle
    - Multiple parents possible
-->

<!-- end_slide -->

# URI Design Best Practices

## Hierarchy & Relationships

```http
/universities                    # All universities
/universities/MIT               # Specific university
/universities/MIT/departments   # Departments at MIT
/universities/MIT/departments/CS # CS department
/universities/MIT/departments/CS/courses # CS courses
/universities/MIT/departments/CS/courses/6.001 # Specific course
```

## Keep It Shallow
❌ Too deep: `/a/b/c/d/e/f/g`
✅ Better: `/resources/123/subresources`

## Use Query Parameters for Filtering
```http
GET /courses?department=CS&level=graduate&year=2024
```

<!--
speaker_note: |
  URI DESIGN PATTERNS (5 minutes)
  
  • Hierarchy example:
    - Natural parent-child relationships
    - Each level is a valid resource
    - Can GET at any level
  
  • Depth guideline:
    - Max 3-4 levels typically
    - Deeper = harder to use
    - Consider flattening
  
  • Query parameters:
    - Filtering, not identification
    - Optional parameters
    - Multiple filters combine
  
  • Common questions:
    - "When to nest vs. top-level?"
    - Clear ownership = nest
    - Shared/independent = top-level
-->

<!-- end_slide -->

# Collection vs Item Resources

## Collection Resources
```http
GET /books           # Get all books
POST /books          # Create new book
DELETE /books        # ⚠️ Delete ALL (rare)
```

## Item Resources
```http
GET /books/123       # Get specific book
PUT /books/123       # Update book
PATCH /books/123     # Partial update
DELETE /books/123    # Delete book
```

## Sub-Resources
```http
GET /books/123/chapters      # Chapters of book 123
POST /books/123/chapters     # Add chapter
GET /books/123/chapters/1    # Specific chapter
```

<!--
speaker_note: |
  COLLECTIONS VS ITEMS (4 minutes)
  
  • Collection patterns:
    - GET: list with pagination
    - POST: create new item
    - DELETE: rarely implemented
  
  • Item patterns:
    - GET: single resource
    - PUT: full replacement
    - PATCH: partial update
    - DELETE: remove resource
  
  • Sub-resources:
    - Clear ownership relationship
    - Parent must exist
    - Deletion cascades
  
  • Design question:
    - /reviews vs /books/123/reviews?
    - Depends on usage patterns
    - Can support both!
-->

<!-- end_slide -->


# Stateless Design Patterns 🔄

## Handling Complex Workflows Statelessly

### Multi-Step Processes
```http
# Each step self-contained
POST /orders/draft
{ "items": [...], "step": "items" }
→ Returns: { "draft_id": "abc123", "next": "/orders/draft/abc123/shipping" }

PUT /orders/draft/abc123
{ "shipping": {...}, "step": "shipping" }
→ Returns: { "draft_id": "abc123", "next": "/orders/draft/abc123/payment" }

POST /orders
{ "draft_id": "abc123", "payment": {...} }
→ Creates final order
```

### Pagination State
```http
# Cursor-based (stateless)
GET /users?cursor=eyJpZCI6MTIzfQ==&limit=20

# Offset-based (stateless but less stable)
GET /users?offset=40&limit=20
```

<!--
speaker_note: |
  STATELESS PATTERNS (5 minutes)
  
  • Module 1 covered basics, now patterns:
  
  • Multi-step workflows:
    - Draft pattern for complex flows
    - Each step has all context
    - Draft ID links steps
    - No server session needed
  
  • Pagination strategies:
    - Cursor: encodes position
    - Stable across inserts/deletes
    - Offset: simple but unstable
  
  • Other patterns:
    - Idempotency keys for retries
    - Request IDs for tracking
    - Correlation IDs for distributed
  
  • Key principle:
    - Client owns workflow state
    - Server owns resource state
-->

<!-- end_slide -->

# Exercise: Resource Modeling 📝

## Scenario: E-Learning Platform

Design resources for:
- Courses and their content
- Student enrollments
- Assignments and submissions
- Grades and feedback
- Discussion forums

**Work in pairs - 5 minutes**

Consider:
- Resource hierarchy
- Relationships
- URI patterns
- HTTP methods

<!-- pause -->

<!--
speaker_note: |
  EXERCISE SETUP (1 minute)
  
  • Form pairs/small groups
  • Give them 5 minutes
  • Walk around, help stuck groups
  • Common struggles:
    - Enrollment: separate resource?
    - Submissions: under assignments or students?
    - Forums: nested or top-level?
-->

<!-- end_slide -->

# Exercise Solution: E-Learning

## Core Resources

```http
# Courses
GET    /courses
GET    /courses/CS101
POST   /courses

# Course Content  
GET    /courses/CS101/modules
GET    /courses/CS101/modules/1/lessons

# Enrollments (junction resource)
POST   /enrollments
DELETE /enrollments/789
GET    /students/123/enrollments
GET    /courses/CS101/enrollments

# Assignments & Submissions
GET    /courses/CS101/assignments
POST   /courses/CS101/assignments
GET    /assignments/456/submissions
POST   /assignments/456/submissions
GET    /students/123/submissions
```

<!--
speaker_note: |
  SOLUTION DISCUSSION (7 minutes)
  
  • Review their solutions first
  • Common approaches:
    - Most nest under courses
    - Enrollment debates
  
  • Key insights:
    - Enrollments as resources
    - Multiple access paths OK
    - Submissions under assignments
  
  • Alternative views:
    - /students/123/courses (enrolled)
    - /assignments/456/submissions
    - /submissions?student=123
  
  • All valid! Depends on:
    - Primary use cases
    - Query patterns
    - Performance needs
-->

<!-- end_slide -->

# Advanced Resource Patterns

## Computed Resources
```http
GET /reports/sales/2024/summary
GET /analytics/user-activity/daily
GET /statistics/system/current
```

## Action Resources (when needed)
```http
# Password reset as a resource
POST /password-resets
{ "email": "user@example.com" }

# Export job as a resource
POST /exports
GET  /exports/job-123/status
GET  /exports/job-123/result
```

## Relationship Resources
```http
# Friendship as a resource
POST   /friendships
DELETE /friendships/456
GET    /users/123/friendships
```

<!--
speaker_note: |
  ADVANCED PATTERNS (5 minutes)
  
  • Computed resources:
    - Don't need storage
    - Calculate on demand
    - Can cache results
  
  • Action resources:
    - Sometimes actions = resources
    - Track state of operation
    - Better than RPC-style
  
  • Relationship resources:
    - Many-to-many relationships
    - Contain relationship metadata
    - Examples: likes, follows, tags
  
  • Key principle:
    - "Can I model this as a resource?"
    - Usually answer is yes!
    - Leads to cleaner design
-->

<!-- end_slide -->

# Resource Representation Patterns

## Sparse Fieldsets
```http
# Client requests specific fields only
GET /users/123?fields=name,email,avatar

# Returns only requested fields
{
  "name": "John Doe",
  "email": "john@example.com",
  "avatar": "https://..."
}
```

## Embedded Resources
```http
# Expand related resources inline
GET /orders/789?expand=customer,items.product

{
  "id": 789,
  "total": 150.00,
  "customer": {  // Expanded inline
    "id": 123,
    "name": "Jane Smith"
  },
  "items": [{
    "quantity": 2,
    "product": {  // Nested expansion
      "id": 456,
      "name": "Widget"
    }
  }]
}
```

<!--
speaker_note: |
  REPRESENTATION PATTERNS (4 minutes)
  
  • Sparse fieldsets:
    - Reduce bandwidth
    - Client-driven optimization
    - Similar to GraphQL flexibility
    - Implementation complexity
  
  • Embedded resources:
    - Reduce round trips
    - expand parameter pattern
    - Can nest expansions
    - Alternative to HATEOAS
  
  • Trade-offs:
    - Flexibility vs caching
    - Complexity vs performance
    - Standard vs custom
  
  • Best practice:
    - Start simple
    - Add patterns as needed
    - Document clearly
-->

<!-- end_slide -->

# Resource State vs Application State

## Resource State
**Data stored on the server**
- User profiles
- Order details  
- Product inventory

## Application State
**Client's current position in workflow**
- Current page
- Form progress
- Shopping cart contents (sometimes)

### RESTful Approach:
- Resource state: on server
- Application state: on client
- Transfer state with each request

```http
# Client tracks state
GET /checkout/step-2
Authorization: Bearer token
X-Cart-Items: 123,456,789
```

<!--
speaker_note: |
  STATE MANAGEMENT (4 minutes)
  
  • Distinction important:
    - Resource = server data
    - Application = client journey
  
  • Shopping cart example:
    - Can be resource (saved cart)
    - Can be client state (session)
    - Depends on requirements
  
  • Benefits of separation:
    - Server simplicity
    - Client flexibility
    - Better scalability
  
  • Practical tip:
    - Long-lived = resource
    - Temporary = client state
    - User-specific = resource
    - Session-only = client state
-->

<!-- end_slide -->

# Common Design Challenges

## Challenge 1: Complex Operations

**Need**: Transfer money between accounts

❌ **RPC Style**:
```http
POST /transferMoney
{ "from": "ACC1", "to": "ACC2", "amount": 100 }
```

✅ **Resource Style**:
```http
POST /transactions
{ 
  "type": "transfer",
  "from_account": "ACC1",
  "to_account": "ACC2",
  "amount": 100
}
```

## Challenge 2: Bulk Operations

```http
# Bulk update
PATCH /books/bulk
{ "ids": [1,2,3], "updates": {"status": "archived"} }

# Or use a batch endpoint
POST /batch
[
  { "method": "PATCH", "url": "/books/1", "body": {...} },
  { "method": "PATCH", "url": "/books/2", "body": {...} }
]
```

<!--
speaker_note: |
  DESIGN CHALLENGES (5 minutes)
  
  • Complex operations:
    - Think "what's the resource?"
    - Transaction IS a resource
    - Has state, can query
  
  • Bulk operations:
    - No perfect REST solution
    - Pragmatic approaches OK
    - Document clearly
  
  • Other challenges:
    - Real-time updates → WebSockets
    - File uploads → multipart/form-data
    - Long operations → async + polling
  
  • Remember:
    - REST is guidelines
    - Pragmatism over purity
    - Consistency matters most
-->

<!-- end_slide -->

# Exercise: Design Challenge 🎯

## Your Task: Design a Food Delivery API

Requirements:
- Browse restaurants and menus
- Shopping cart management
- Place and track orders
- Handle payments
- Driver assignment and tracking

**10 minutes** - Design the main resources and endpoints

Consider:
- What are the resources?
- How do they relate?
- What about the ordering workflow?
- How to handle real-time tracking?

<!--
speaker_note: |
  MAJOR EXERCISE (2 minutes setup)
  
  • This is a big exercise
  • Tests everything learned
  • Let them struggle a bit
  
  • Common questions:
    - Cart: resource or client state?
    - Payment: separate service?
    - Tracking: REST or WebSocket?
  
  • No perfect answer!
  • Multiple valid approaches
  • Focus on reasoning
-->

<!-- end_slide -->

# Exercise Solution: Food Delivery

## Core Resources

```http
# Restaurants & Menus
GET  /restaurants
GET  /restaurants/123
GET  /restaurants/123/menu
GET  /restaurants?location=x,y&cuisine=italian

# Cart (as resource for persistence)
POST   /carts              # Create cart
GET    /carts/abc         # Get cart
PUT    /carts/abc/items   # Update items
DELETE /carts/abc         # Clear cart

# Orders
POST /orders              # Place order
{
  "cart_id": "abc",
  "delivery_address": {...},
  "payment_method": "card_xyz"
}
GET  /orders/789          # Order details
GET  /orders/789/status   # Current status

# Order Tracking
GET  /orders/789/tracking  # Location updates
WS   /orders/789/live      # WebSocket for real-time
```

<!--
speaker_note: |
  SOLUTION REVIEW (8 minutes)
  
  • Review participant solutions
  • Common patterns emerge:
    - Most model cart as resource
    - Orders separate from cart
    - Tracking challenges
  
  • Key decisions explained:
    - Cart as resource: persistence
    - Order immutable once placed
    - Status vs tracking separation
    - WebSocket for real-time
  
  • Alternative approaches:
    - Cart in client only
    - Payments as sub-resource
    - Driver as separate resource
  
  • Discussion points:
    - Why these choices?
    - Trade-offs?
    - Real-world constraints?
-->

<!-- end_slide -->


# Key Takeaways 🎯

## Advanced Resource Design

1. **Resources Beyond Tables**
   - Virtual & computed resources
   - Cross-domain aggregations
   - Process resources

2. **Flexible Access Patterns**
   - Multiple paths to same data
   - Composite resources
   - Sparse fieldsets & expansion

3. **Stateless Workflows**
   - Draft pattern for multi-step
   - Cursor-based pagination
   - Client owns workflow state

4. **Pragmatic Design**
   - Start simple, evolve as needed
   - Document your choices
   - Consistency over perfection

<!--
speaker_note: |
  MODULE SUMMARY (3 minutes)
  
  • Reinforce key concepts:
    - Resources central to REST
    - Good design = good API
    - Patterns provide consistency
  
  • Common mistakes to avoid:
    - RPC thinking
    - Stateful design
    - Over-nesting
    - Verb obsession
  
  • Moving forward:
    - These principles apply everywhere
    - Jersey makes it easy
    - Practice makes perfect
  
  • Questions before break?
  • Next: Jersey setup
-->

<!-- end_slide -->

# Q&A and Break ☕

## Questions?

### Coming Next:
**Module 3: Jersey Framework Setup**
- Project configuration
- JAX-RS basics
- First Jersey endpoint

**Break: 10 minutes**

<!--
speaker_note: |
  Q&A AND BREAK (10 minutes)
  
  • Common questions:
    - Real-world compromises?
    - Legacy system migration?
    - Microservices patterns?
  
  • If time permits:
    - Show real API examples
    - GitHub, Twitter, Stripe
  
  • Break logistics:
    - 10 minutes
    - Back at [specific time]
    - Jersey setup next
  
  • Prep for Module 3:
    - Ensure Java 8 ready
    - IDE opened
    - Ready for hands-on
-->

<!-- end_slide -->