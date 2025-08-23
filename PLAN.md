# DBH REST with Jersey Training - Preparation Plan

## Overview

This document outlines the preparation plan for a 2-day REST API training
focused on Jersey (plain Java) for DBH. The training emphasizes hands-on
exercises with Jersey, REST fundamentals, security implementations, and Jackson
for JSON processing.

## Customer Requirements Summary

Based on meeting notes with DBH and subsequent updates:

1. **Primary Focus**: Plain Java with Jersey (not Spring Boot)
1. **Target Java Version**: Java 8 (critical requirement from Daniel Morrison, Aug 4)
1. **Participants**: Have basic HTTP knowledge but lack REST concepts
1. **Duration**: 2 days with 1-2 hours Q&A at the end
1. **Key Topics**: REST fundamentals, versioning, security, documentation
1. **Exercises**: Extensive hands-on work with Jersey
1. **Code Examples**: Will NOT be provided by participants (confirmed Aug 4)

## Technical Prerequisites Update

**Important Note**: Initial requirements sent to participants specified Java 21,
but DBH confirmed they work on Java 8. All training materials will be Java 8
compatible.

### Updated Requirements for Participants

1. **Java Development Kit 8** (not Java 21 as initially communicated)
1. IntelliJ IDEA or comparable IDE with Java 8 support
1. Gradle 8.5+ (backwards compatible with Java 8)
1. REST Client (Postman, Insomnia, or similar)
1. curl CLI installed
1. Git for accessing training materials
1. GitHub access for repository

### Java 8 Compatibility Notes

- Jersey 2.x (not 3.x which requires Java 11+)
- Jackson 2.x versions compatible with Java 8
- No use of Java 9+ features (modules, var keyword, etc.)
- Lambda expressions and Stream API available but with Java 8 limitations

## Training Agenda

The detailed training agenda has been moved to [agenda.md](./agenda.md) for better
organization and easier sharing with participants.

## Preparation Timeline

### Week 1: Research & Planning (5 days)

1. **Day 1-2: Technical Research**

   - Jersey best practices and patterns
   - Jersey vs Spring Boot comparison
   - REST API versioning approaches
   - Security implementation patterns

1. **Day 3: Training Materials Research**

   - Analyze existing Jersey trainings
   - Review enterprise REST patterns
   - Study adult learning principles

1. **Day 4: Customer Alignment**

   - Create generic enterprise examples (no participant code available)
   - Finalize agenda with customer
   - Adjust based on skill levels
   - Focus on common Java 8 enterprise patterns

1. **Day 5: Planning Finalization**
   - Create detailed module outlines
   - Plan exercise progression
   - Set up development environment

### Week 2-3: Content Development (10 days)

1. **Days 1-3: Day 1 Content**

   - [x] REST fundamentals slides (01-rest-fundamentals.md)
   - [x] Resource design slides (02-resource-design.md)
   - [x] Idempotency & alternatives slides (03-idempotency-alternatives.md)
   - [x] Jersey setup slides (04-jersey-setup.md)
   - [x] Jersey CRUD exercise slides (05-jersey-crud-exercise.md)
   - [x] Bean validation slides (06-bean-validation.md)
   - [x] API versioning slides (08-api-versioning.md)
   - [x] Spring Boot overview slides (08-spring-boot-overview.md)
   - [x] Basic exercises and solutions (Exercise 01, 02, 03 completed)
   - [ ] Versioning implementation guide

1. **Days 4-5: Day 2 Content**

   - [ ] Jackson basics slides (06-jackson-basics.md)
   - [ ] Jackson advanced slides (07-jackson-advanced.md)
   - [ ] Security implementation slides (08-security-implementation.md)
   - [ ] Security implementation guides
   - [ ] Advanced exercises
   - [ ] Showcase preparations

1. **Days 6-8: Exercise Development**

   - Progressive Jersey exercises
   - Security scenarios
   - Integration challenges
   - Final project specification

1. **Days 9-10: Supporting Materials**
   - Setup instructions
   - Reference guides
   - Troubleshooting guide
   - Additional resources

### Week 4: Review & Refinement (5 days)

1. **Days 1-2: Content Review**

   - Technical accuracy check
   - Exercise testing
   - Time allocation verification

1. **Day 3: Dry Run Day 1**

   - Practice delivery
   - Timing adjustments
   - Exercise validation

1. **Day 4: Dry Run Day 2**

   - Security demos
   - Complex exercises
   - Q&A preparation

1. **Day 5: Final Preparation**
   - Material packaging
   - Environment setup
   - Backup preparations

## Research Tasks

### Technical Research

1. **Jersey Framework (Java 8 Compatible)**

   - Jersey 2.x features (2.35 or 2.39 for Java 8)
   - Best practices for enterprise Java 8 environments
   - Common patterns and anti-patterns
   - Performance optimization for Java 8

1. **Jersey vs Spring Boot**

   - Detailed comparison matrix
   - Use case scenarios
   - Migration considerations
   - Pros and cons analysis

1. **REST API Versioning**

   - Industry standards
   - Real-world examples
   - Implementation strategies
   - Version deprecation

1. **Security Implementations (Java 8)**
   - JWT in Jersey with Java 8 compatible libraries
   - OAuth2 integration for Java 8
   - API key patterns
   - Certificate authentication in Java 8

### Training Methodology Research

1. **Existing Trainings Analysis**

   - Jersey-specific courses
   - REST API workshops
   - Enterprise training formats
   - Success metrics

1. **Adult Learning Principles**
   - Hands-on learning effectiveness
   - Knowledge retention strategies
   - Exercise design patterns
   - Feedback mechanisms

## Deliverables

### Training Materials

1. **Slide Decks**

   - 9 comprehensive modules (2 of 9 completed)
     - [x] 01-rest-fundamentals.md
     - [x] 02-resource-design.md
     - [ ] 03-jersey-setup.md
     - [ ] 04-jersey-crud.md
     - [ ] 04b-bean-validation.md
     - [ ] 05-api-versioning.md
     - [ ] 06-jackson-basics.md
     - [ ] 07-jackson-advanced.md
     - [ ] 08-security-implementation.md
   - Code examples included
   - Visual diagrams
   - Progressive complexity

1. **Exercise Workbooks**

   - Step-by-step instructions
   - Starter code templates
   - Validation criteria
   - Extension challenges

1. **Instructor Guide**

   - Detailed timing
   - Key talking points
   - Common questions
   - Troubleshooting tips

1. **Participant Materials**
   - Setup instructions
   - Reference sheets
   - Resource links
   - Post-training support

### Code Repositories

1. **Starter Projects**

   - Basic Jersey setup
   - Exercise templates
   - Configuration examples

1. **Solution Repository**

   - Complete solutions
   - Alternative approaches
   - Best practice examples

1. **Showcase Projects**
   - Security demonstrations
   - OpenAPI integration
   - Client certificate setup

## Success Metrics

1. **Pre-Training**

   - Environment setup success rate
   - Participant preparedness
   - Code example quality

1. **During Training**

   - Exercise completion rates
   - Question engagement
   - Concept understanding

1. **Post-Training**
   - Participant satisfaction
   - Knowledge application
   - Follow-up questions
   - Real-world implementation

## Solution Management Strategy

### Directory Structure
- `/starter-project/` - Skeleton code with TODOs for attendees
- `/instructor-solution/` - Complete solutions for instructor

### Branch Strategy

**Main Branch:**
- Contains the FIRST complete solution for each file
- Example: `UserResource.java` (Exercise 02) → first solution on main
- Example: `ValidationExceptionMapper.java` (Exercise 03) → first solution on main
- All "first appearance" solutions accumulate in `/instructor-solution/` on main

**Solution Branches:**
- Only for exercises that MODIFY existing files
- `solution/03-validation` - UserResource with validation added
- `solution/04-versioning` - UserResource with versioning added
- `solution/05-jackson` - UserResource with Jackson features
- `solution/06-jackson-advanced` - Advanced Jackson modifications
- `solution/07-security` - Security additions
- `solution/08-final` - Final project modifications

### Instructor Workflow
1. Show skeleton in `/starter-project/`
2. Live code or show solution from `/instructor-solution/`
3. For modified files, checkout solution branch
4. Use `git diff` to show evolution
5. New files always shown from main first

### Maintenance Notes
- Minimize duplication
- Main branch is always complete and runnable
- Branches only contain deltas
- Test each branch before training

## Risk Mitigation

1. **Technical Risks**

   - Multiple IDE setup guides
   - Docker-based alternatives with Java 8
   - Offline documentation
   - Java version compatibility checks

1. **Java 8 Compatibility Risks**

   - Verify all libraries work with Java 8
   - Test all code examples in Java 8 environment
   - Prepare fallback for participants with Java 21
   - Document Java 8 specific limitations

1. **Timing Risks**

   - Buffer time in schedule
   - Optional exercises
   - Flexible Q&A allocation

1. **Skill Level Risks**
   - Basic and advanced tracks
   - Peer programming options
   - Additional resources
   - No participant code examples - use generic patterns

## Comprehensive Task List

### Project Structure

```
dbh-rest-with-jackson/
├── starter-project/       # Skeleton with infrastructure only (participants get this)
├── exercises/             # Individual exercise modules (01-10)
│   └── XX-name/
│       ├── slides.md      # Exercise-specific slides (stored in /slides/)
│       ├── README.md      # Written instructions for students
│       └── INSTRUCTOR_NOTES.md  # Teaching notes
├── instructor-solution/   # Complete implementation (instructor reference)
├── showcases/           # Demo-only materials
├── slides/             # All presentation materials
│   └── presenterm/     # Presenterm slide files
│       ├── 01-rest-basics.md
│       ├── 02-jersey-crud.md
│       └── ...
└── instructor-guide/   # Teaching notes and timing
```

### Exercise Structure Standards

Based on the successful Exercise 02 pattern, all exercises should follow this structure:

#### README Location and Structure
- **MUST stay in exercise folder** (e.g., `/exercises/02-jersey-crud/README.md`)
- Serves as self-study reference and backup instructions
- Contains comprehensive step-by-step instructions with:
  - Clear time allocations and objectives
  - Prerequisites and background
  - Numbered tasks with time estimates
  - TDD workflow explanation
  - Running tests section with exact commands
  - Expected test output examples
  - Technical hints and tips
  - Bonus tasks for fast finishers
  - Documentation links and resources
  - Common mistakes with ❌ emoji
  - Solution checkpoint checklist
  - "Need Help?" section

#### Slide Location and Structure
- **Slides go in** `/slides/presenterm/XX-exercise-name.md`
- Used for live instructor-led presentation
- Screen-friendly with multiple short slides
- Each task slide follows pattern:
  - Documentation link (📚 emoji)
  - Hint (💡 emoji)
  - `<!-- pause -->` for reveal
  - Code example
  - Expected result
- Include checkpoint slides at 20 min, 40 min
- Use `<!-- end_slide -->` to keep slides screen-sized
- Progressive revelation for better flow
- Speaker notes for instructor guidance

#### Alignment Requirements
- Tasks in slides MUST match README tasks exactly
- Same numbering, same time allocations
- Identical bonus tasks
- Consistent test commands
- Both reference same documentation links

### Exercise Slide Guidelines

Each exercise gets concise slides (stored in `/slides/presenterm/XX-exercise-name.md`):
- Keep slides short - must fit on screen
- Focus on code examples and key concepts
- Include checkpoints at 20 and 40 minutes
- Add speaker notes for instructor guidance
- Run with: `presenterm slides/presenterm/XX-exercise-name.md`

### AI Model Strategy (Anthropic Max Plan)

- **claude-opus-4.1**: Complex tasks - architecture, security, advanced patterns, critical design
- **claude-sonnet-4**: Everything else - standard exercises, documentation, basic implementations
- **Multiple models**: Critical decisions requiring comparison
- Note: With Max Plan, using Sonnet 4 as minimum ensures quality for all tasks

### PHASE 0: COMPREHENSIVE RESEARCH

#### 0.1 Technology Research [3 hours]

**Research Tasks** (Use Brave Search, Firecrawl, Context7):

- [x] Jersey 2.x Java 8 compatibility and best practices (Context7: /jersey/jersey)
- [x] Jackson 2.x with Java 8 patterns (Context7: /fasterxml/jackson)
- [x] Embedded server comparison: Jetty vs Grizzly
- [x] JWT libraries for Java 8 (jjwt vs java-jwt vs nimbus-jose-jwt)
- [x] REST API design patterns 2024/2025
- [x] Enterprise security patterns for REST APIs
      **Tools**: Brave Search for articles, Firecrawl for docs, Context7 for library references
      **Model**: claude-sonnet-4

#### 0.2 Training Material Research [2 hours]

**Research Tasks**:

- [x] Existing Jersey training materials and courses
- [x] REST API workshop structures
- [x] Adult learning principles for technical training
- [x] Hands-on exercise design patterns
- [x] Common pitfalls in REST API training
      **Tools**: Brave Search for training resources, Firecrawl for course outlines
      **Model**: claude-sonnet-4

### PHASE 1: FOUNDATION (Critical Path - Sequential) ✅ RESTRUCTURED

#### 1.1 Starter Project Infrastructure [4 hours] ✅ COMPLETED

**What We Created**:
- [x] `starter-project/` - Skeleton with just infrastructure
  - [x] Application.java (server setup)
  - [x] JerseyConfig (basic configuration)
  - [x] CORS and Logging filters
  - [x] ErrorResponse DTO (for error handling)
  - [x] Stub models with TODOs (User, Product, Order)
  - [x] build.gradle with all dependencies
  - [x] README with exercise instructions

#### 1.2 Instructor Solution [2 hours] ✅ COMPLETED

- [x] `instructor-solution/` - Complete reference implementation
  - [x] All models with validation
  - [x] All DTOs with mappings
  - [x] ModelMapper utility
  - [x] Complete resources (to be added in 1.3)
  - [x] Full exception handling

**Note**: Domain models, DTOs, and validation are now EXERCISE TASKS, not base setup

#### 1.3 REST Template [3 hours] ✅ COMPLETED

- [x] Create AbstractResource base class
- [x] Implement ExampleUserResource with all HTTP methods
- [x] Add exception handling (NotFoundException, ValidationException)
- [x] Create ErrorResponse DTO
- [x] Implement request/response logging filter
- **Agent**: backend-developer
- **Model**: opus-4.1

#### 1.4 Test Infrastructure [3 hours] ✅ COMPLETED

- [x] Configure JUnit 5 and REST Assured
- [x] Create BaseIntegrationTest with server lifecycle
- [x] Add TestDataBuilder for entities
- [x] Create custom assertions
- [x] Write example integration test
- **Agent**: backend-developer
- **Model**: opus-4.1

### PHASE 2: PARALLEL EXERCISE DEVELOPMENT

Can be done simultaneously by multiple agents after Phase 1 completion.

#### Day 1 Training Exercises

##### 2.1 REST Fundamentals Exercise [3 hours]

**Pre-task Research**:

- [x] Research REST maturity models (Richardson, etc.)
- [x] Find real-world REST API examples (Brave Search)
- [x] Research common REST anti-patterns
- [x] Get latest REST API design guidelines
      **Implementation**:
- [x] Create exercises/01-rest-basics/README.md
- [x] Create exercises/01-rest-basics/solution.md
- [x] Create REST principles and HTTP methods exercise
- [x] Design resources for library management system
- **Agent**: general-purpose
- **Model**: claude-sonnet-4 (medium complexity)

##### 2.2 Jersey Setup Exercise [15-20 min within 60 min slot]

- [x] Create exercises/02-jersey-setup/
- [x] Create basic project setup exercise
- **Note**: Fits within "Plain Java with Jersey Setup" (Day 1 PM)

##### 2.3 Jersey CRUD Exercise [45-50 min within 75 min slot]

- [x] Create exercises/03-jersey-crud/
- [x] **Trainees implement**: Complete User model (partial starter provided)
- [x] **Trainees implement**: Add GET and POST operations
- [x] **Trainees implement**: Add PUT and DELETE operations
- [x] **Trainees implement**: Basic error handling for 404
- [x] Provide test cases they must pass
- **Note**: Fits within "Building REST Controllers with Jersey" (Day 1 PM)
- **Model**: claude-sonnet-4

##### 2.4 Bean Validation Exercise [15-20 min within 30 min slot]

- [x] Create exercises/04-bean-validation/
- [x] **Trainees add**: Validation annotations to User model
- [x] **Trainees implement**: @Valid in POST/PUT methods
- [x] **Trainees test**: With provided invalid data
- [x] Provide clear validation requirements
- **Note**: Fits within "Bean Validation with Jersey" (Day 1 PM)
- **Model**: claude-sonnet-4

##### 2.5 API Versioning Exercise [20-25 min within 45 min slot]

- [x] Create exercises/05-api-versioning/
- [x] **Trainees implement**: Simple /v1 to /v2 migration
- [x] **Trainees add**: One new field in v2
- [x] **Trainees test**: Both versions work
- **Note**: Fits within "API Versioning Strategies" (Day 1 PM)
- **Model**: claude-sonnet-4

#### Day 2 Training Exercises

##### 2.6 Jackson Basics Exercise [45 min within 90 min slot]

- [ ] Create exercises/06-jackson-basics/
- [ ] **Trainees configure**: Jackson ObjectMapper
- [ ] **Trainees implement**: Date formatting
- [ ] **Trainees add**: @JsonProperty annotations
- [ ] **Trainees test**: Serialization/deserialization
- **Note**: Fits within "Jackson Integration" (Day 2 AM)
- **Model**: claude-sonnet-4

##### 2.7 Jackson Advanced Exercise [30 min within 60 min slot]

- [ ] Create exercises/07-jackson-advanced/
- [ ] **Trainees create**: One custom serializer
- [ ] **Trainees implement**: @JsonIgnore and @JsonView
- [ ] **Trainees test**: Different JSON representations
- **Note**: Fits within "Advanced Jackson Features" (Day 2 AM)
- **Model**: claude-sonnet-4

##### 2.8 Security Implementation Exercise [30-40 min within 60 min slot]

- [ ] Create exercises/08-security-implementation/
- [ ] **Trainees implement**: Basic Auth filter
- [ ] **Trainees add**: Simple role checking
- [ ] **Trainees test**: Protected endpoints
- [ ] Provide security filter template
- **Note**: Fits within "Security Implementation" (Day 2 PM)
- **Model**: claude-sonnet-4

### PHASE 3: SHOWCASES, DOCUMENTATION & QUALITY

#### Showcases (Demo Only)

##### 3.1 Client Certificate Authentication [3 hours]

- [ ] Create showcases/client-certificates/
- [ ] Generate self-signed certificates
- [ ] Configure mutual TLS in Jersey
- [ ] Create demo script with curl commands
- [ ] Document setup process
- **Agent**: backend-developer
- **Model**: opus-4.1 (complex security)

##### 3.2 OpenAPI/Swagger Integration [2 hours]

- [ ] Create showcases/openapi/
- [ ] Add Swagger annotations to resources
- [ ] Integrate Swagger UI
- [ ] Generate API documentation
- [ ] Create usage examples
- **Agent**: documentation-specialist
- **Model**: claude-sonnet-4 (documentation task)

##### 3.3 Container Deployment [1 hour]

- [ ] Create showcases/docker/
- [ ] Create Dockerfile for Java 8 app
- [ ] Docker Compose with PostgreSQL
- [ ] Basic Kubernetes deployment YAML
- **Agent**: general-purpose
- **Model**: claude-sonnet-4

#### Documentation & Support

##### 2.9 Comprehensive Exercise [90 min - Day 2 PM]

- [ ] Create exercises/09-comprehensive/
- [ ] **Trainees build**: Complete REST API for a Todo application
- [ ] **Combines**: CRUD, validation, JSON handling, basic security
- [ ] **Trainees implement**: All concepts from Day 1 and 2
- [ ] Provide detailed requirements and test suite
- **Note**: Fits within "Comprehensive Exercise" (Day 2 PM)
- **Model**: claude-sonnet-4

##### 3.5 Instructor Materials [4 hours]

- [x] Create instructor-guide/README.md with timing
- [ ] Common questions and answers
- [ ] Troubleshooting guide
- [ ] Alternative explanations
- [ ] Exercise solutions with explanations
- **Agent**: documentation-specialist
- **Model**: opus-4.1

##### 3.6 Slides and Presentations [8 hours total]

**Slide Creation Tasks by Module:**

**Day 1 Slides:**

- [x] Create slides/presenterm/01-rest-fundamentals.md - HTTP/REST Basics (75 min content)
- [x] Create slides/presenterm/02-resource-design.md - Resource Orientation & REST Principles (60 min content)
- [x] Create slides/presenterm/03-idempotency-alternatives.md - Idempotency & REST Alternatives (45 min content)
- [x] Create slides/presenterm/04-jersey-setup.md - Plain Java with Jersey Setup (60 min content)
- [x] Create slides/presenterm/05-jersey-crud-exercise.md - Jersey CRUD Exercise (45-50 min hands-on)
- [x] Create slides/presenterm/06-bean-validation.md - Bean Validation with Jersey (30 min content)
- [x] Create slides/presenterm/07-bean-validation-exercise.md - Bean Validation Exercise (20 min hands-on)
- [x] Create slides/presenterm/08-api-versioning.md - API Versioning Strategies (45 min content)
- [x] Create slides/presenterm/09-api-versioning-exercise.md - API Versioning Exercise (25 min hands-on)
- [x] Create slides/presenterm/08-spring-boot-overview.md - Spring Boot Overview (30 min content)

**Day 2 Slides:**

- [ ] Create slides/presenterm/09-jackson-basics.md - Jackson Integration with Jersey (90 min content)
- [ ] Create slides/presenterm/10-jackson-advanced.md - Advanced Jackson Features (60 min content)
- [ ] Create slides/presenterm/11-security-implementation.md - Security Fundamentals & Implementation (90 min content)

**Supporting Materials:**

- [x] Create slides/ folder structure
- [x] Visual diagrams for REST concepts (in slides 01-02)
- [ ] Code examples and live coding notes for each module
- [ ] Architecture diagrams for system design topics
- [ ] Create master presentation index/README

- **Agent**: general-purpose
- **Model**: opus-4.1 (visual content creation)

### Quality Assurance (Ongoing)

#### 3.7 Java 8 Compatibility Validation

- [ ] Test all code in actual Java 8 JVM
- [ ] Verify library compatibility
- [ ] Check for Java 9+ features
- [ ] Create compatibility report
- **Agent**: code-reviewer
- **Model**: opus-4.1

#### 3.8 Exercise Validation & Timing

- [ ] Test each exercise completion time
- [ ] Assess difficulty levels
- [ ] Verify solutions work correctly
- [ ] Adjust based on feedback
- **Agent**: Multiple reviewers for consensus

### Multi-Model Comparison Tasks

For critical components, use multiple AI models and compare:

#### Security Architecture Design

- **opus-4.1**: Primary analysis and implementation
- **o3**: Deep security analysis (if opus-4.1 needs validation)
- **gemini-2.5-pro**: Alternative practical approach
- Choose opus-4.1 unless specific security depth needed

#### Final Project Specification

- **opus-4.1**: Primary design and integration
- **o3**: Complex integration validation
- **gemini-2.5-pro**: Real-world scenario alternatives
- Default to opus-4.1 for consistency

#### Jersey vs Spring Boot Comparison

- Create comparison matrix
- Performance benchmarks
- Developer experience analysis
- Use multiple perspectives

## Execution Strategy

### Week 1: Foundation & Core Exercises

- Days 1-2: Complete Phase 1 (Foundation)
- Days 3-5: Start Phase 2 (Parallel exercises)

### Week 2: Advanced & Polish

- Days 1-2: Complete remaining exercises
- Days 3-4: Showcases and documentation
- Day 5: Quality assurance and final validation

### Success Metrics

- All exercises completable in allocated time
- Java 8 compatibility 100% verified
- Security implementations follow best practices
- Progressive difficulty curve validated
- Instructor can deliver with confidence

## Research Tools Strategy

### Web Research Tools Usage:

1. **Context7** (`/resolve-library-id` then `/get-library-docs`):

   - Primary source for library documentation
   - Jersey: `/jersey/jersey`
   - Jackson: `/fasterxml/jackson-databind`
   - JWT libraries: `/auth0/java-jwt`, `/jwtk/jjwt`
   - Testing: `/rest-assured/rest-assured`

2. **Brave Search** (`brave_web_search`):

   - Latest best practices and patterns
   - Training methodologies
   - Security vulnerabilities and fixes
   - Community solutions and discussions

3. **Firecrawl** (`firecrawl_scrape`, `firecrawl_map`):
   - Official documentation deep dives
   - OWASP guidelines
   - Enterprise REST API examples
   - Course syllabi from other trainings

### Research-First Approach Benefits:

- Ensures Java 8 compatibility before implementation
- Discovers latest security vulnerabilities
- Finds proven patterns and anti-patterns
- Validates architectural decisions
- Reduces rework and technical debt

## Next Actions

1. ~~**Execute Phase 0 Research**~~ ✅ COMPLETED
1. **Create base-project folder structure** (Critical - Do First)
1. Set up Gradle build with Java 8 configuration
1. Implement domain models
1. Launch parallel exercise development with multiple agents
1. Begin Java 8 compatibility testing early

## Appendix: Research Queries

### Jersey and REST (Java 8 Focus)

- "Jersey 2.x Java 8 best practices"
- "Jersey 2.35 vs 2.39 Java 8 compatibility"
- "Jersey vs Spring Boot REST comparison Java 8"
- "Enterprise Jersey patterns Java 8"
- "Jersey security implementations Java 8"
- "Jackson 2.x with Jersey 2.x Java 8"

### Training Design

- "Technical training best practices"
- "REST API workshop structure"
- "Hands-on coding exercises design"
- "Enterprise developer training"

### Security Topics

- "JWT implementation Jersey"
- "REST API security patterns"
- "Client certificate authentication Java"
- "API versioning strategies"
