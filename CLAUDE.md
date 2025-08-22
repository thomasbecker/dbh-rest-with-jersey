# CLAUDE.md - DBH REST with Jersey Training

This file provides guidance to Claude Code when working with the DBH REST training materials.

## Project Overview

This repository contains training materials for a 2-day REST API workshop using Jersey (plain Java, no Spring Boot) for DBH. The training targets Java 8 and emphasizes hands-on exercises.

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

## Development Workflow

1. Research using Context7, Brave Search, and Firecrawl
2. Create base project structure
3. Develop exercises progressively
4. Create slides in Presenterm format
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

## Solution Management Strategy

### Directory Structure
- `/starter-project/` - Always contains skeleton code with TODOs for attendees to complete
- `/instructor-solution/` - Contains complete solutions for instructor reference

### Branch Strategy

**Main Branch:**
- Contains the FIRST complete solution for each file in `/instructor-solution/`
- Example: `UserResource.java` first appears in Exercise 02, so its Exercise 02 solution lives in `/instructor-solution/` on main
- Example: If `ValidationExceptionMapper.java` first appears in Exercise 03, its Exercise 03 solution also lives on main

**Solution Branches:**
- Only created when existing files need MODIFICATIONS in later exercises
- Branch naming: `solution/03-validation`, `solution/04-versioning`, etc.
- Example: `solution/03-validation` contains updated `UserResource.java` with validation added
- Example: `solution/04-versioning` contains updated `UserResource.java` with versioning added
- New files introduced in that exercise still go to main branch first

### Key Principles
1. Each file's initial solution goes in main branch
2. Branches only for exercises that modify existing files
3. Starter-project always has skeletons/TODOs
4. Instructor-solution accumulates all "first appearance" solutions on main
5. Branches only contain delta changes to existing files
6. Less duplication, clear progression

### Instructor Workflow
```bash
# Show initial solution (from main)
cat instructor-solution/src/main/java/com/dbh/training/rest/resources/UserResource.java

# Show how file evolves in Exercise 03
git checkout solution/03-validation
git diff main..HEAD -- instructor-solution/src/main/java/com/dbh/training/rest/resources/UserResource.java

# Return to main for new files
git checkout main
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
- no co-authored-by stuff when doing commits
- remember that this repo will be split into multiple smaller repos later.
- Remember how to run gradle and the tests with the correct java version, etc.
- no auto commits. I want to commit manually