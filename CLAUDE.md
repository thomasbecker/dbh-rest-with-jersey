# CLAUDE.md - DBH REST with Jersey Training

This file provides guidance to Claude Code when working with the DBH REST training materials.

## Project Overview

This repository contains training materials for a 2-day REST API workshop using Jersey (plain Java, no Spring Boot) for DBH. The training targets Java 8 and emphasizes hands-on exercises.

## Slide Creation with term_deck

The training slides will be created using term_deck, a terminal-based presentation tool located at `/Users/tbecker/workspaces/term_deck`.

### Creating Slides

Slides should be created in markdown format with the following structure:

```markdown
---
title: REST API Training with Jersey
author: Thomas Becker
subtitle: Day 1 - REST Foundations & Jersey Framework
---

## Slide Title

Slide content here

<!-- end_slide -->

## Next Slide

More content
```

### Adding Code Examples

Code blocks are supported with syntax highlighting for Java, Python, and Rust:

```markdown
## Jersey REST Controller Example

```java
@Path("/users")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<User> users = userService.findAll();
        return Response.ok(users).build();
    }
}
```

<!-- end_slide -->
```

### Adding Images

Images can be embedded using standard markdown syntax:

```markdown
## REST Architecture

![REST diagram](./images/rest-architecture.png)

Key principles:
- Stateless communication
- Resource-based
- Uniform interface

<!-- end_slide -->
```

### Running Presentations

To run a presentation:

```bash
cd /Users/tbecker/workspaces/term_deck
cargo run /path/to/slides.md
```

Navigation:
- `h` - Previous slide
- `l` - Next slide
- `t` - Change theme
- `q` - Quit

### Slide Organization

Create separate markdown files for each training module:
- `slides/01-rest-fundamentals.md`
- `slides/02-jersey-setup.md`
- `slides/03-crud-operations.md`
- `slides/04-versioning.md`
- `slides/05-jackson.md`
- `slides/06-security.md`
- `slides/07-final-project.md`

### Best Practices for Training Slides

1. **Keep slides concise** - Terminal space is limited
2. **Use code examples liberally** - Java code with syntax highlighting
3. **Include diagrams** - Save as PNG in `slides/images/`
4. **Progressive disclosure** - Build concepts step by step
5. **Mark exercise slides** - Clear indication when to switch to hands-on

### Example Training Slide

```markdown
## Exercise: Create UserResource

**Task**: Implement a complete CRUD resource

**Requirements**:
- GET /users - List all users
- GET /users/{id} - Get single user
- POST /users - Create user
- PUT /users/{id} - Update user
- DELETE /users/{id} - Delete user

**Time**: 30 minutes

<!-- end_slide -->
```

## Slide Creation with Presenterm

Presenterm is an alternative terminal presentation tool with additional features. Located at https://github.com/mfontanini/presenterm

### Presenterm Syntax

#### Slide Separators
- `---` to separate slides (standard)
- `<!-- end_slide -->` for explicit slide ending

#### Code Blocks with Features
```markdown
```java +line_numbers
@Path("/users")
public class UserResource {
    // code here
}
```

```java {1-3|5-8|all}  // Dynamic line highlighting
// First highlight lines 1-3
// Then highlight lines 5-8
// Finally highlight all
```
```

#### Speaker Notes
```markdown
## Slide Title

Content visible to audience

<!-- speaker_note: This is a single line speaker note -->

More content

<!-- 
speaker_note: |
  This is a multiline speaker note.
  Remember to mention:
  - Point 1
  - Point 2
  - Point 3
-->
```

#### Layouts and Columns
```markdown
<!-- column_layout: [7, 3] -->

<!-- column: 0 -->
## Main Content
Left column with 70% width

<!-- column: 1 -->
### Sidebar
Right column with 30% width

<!-- reset_layout -->
```

#### Pauses and Animations
```markdown
## Progressive Content

First point

<!-- pause -->

Second point appears after pause

<!-- pause -->

Third point
```

#### Executable Code
```markdown
```bash +exec
echo "This will execute when shown"
```
```

### Running Presenterm
```bash
# Install presenterm
cargo install presenterm

# Run presentation (basic)
presenterm slides/presentation.md

# With speaker notes - Terminal 1 (presenter view)
presenterm slides/presentation.md --publish-speaker-notes

# With speaker notes - Terminal 2 (listener for notes)
presenterm slides/presentation.md --listen-speaker-notes

# Or configure to always publish in ~/.config/presenterm/config.yaml
# speaker_notes:
#   always_publish: true
```

### Presenterm vs term_deck Comparison

| Feature | term_deck | Presenterm |
|---------|-----------|------------|
| Basic slides | ✅ | ✅ |
| Code highlighting | ✅ | ✅ Enhanced |
| Images | ✅ | ✅ + GIFs |
| Speaker notes | ❌ | ✅ |
| Columns | ❌ | ✅ |
| Pauses | ❌ | ✅ |
| Live reload | ❌ | ✅ |
| Themes | ✅ | ✅ More options |
| Export PDF | ❌ | ✅ |

## Development Workflow

1. Research using Context7, Brave Search, and Firecrawl
2. Create base project structure
3. Develop exercises progressively
4. Create slides in BOTH term_deck and presenterm formats
5. Test all code with Java 8
6. Validate exercise timing

## Key Commands

```bash
# Build base project
cd base-project
./gradlew build

# Run tests
./gradlew test

# Start server
./gradlew run

# Test with curl
curl -X GET http://localhost:8080/api/users
```

## Java 8 Compatibility Checklist

- [ ] No var keyword
- [ ] No modules (module-info.java)
- [ ] No text blocks
- [ ] No switch expressions
- [ ] Use Jersey 2.35 (not 3.x)
- [ ] Use Jackson 2.14.x
- [ ] Lambda expressions OK
- [ ] Stream API OK