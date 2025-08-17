# Training Slides

This directory contains presentation slides for the DBH REST API training in two
formats:

## Formats

### 1. term_deck
- Simple terminal-based presentation tool
- Located in `/term_deck/` subdirectory
- Basic markdown with slide separators
- Good for simple presentations

### 2. presenterm
- Advanced terminal presentation tool
- Located in `/presenterm/` subdirectory
- Supports speaker notes, columns, pauses
- Better for training with instructor notes

## Slide Modules

1. **01-rest-fundamentals.md** - REST principles and concepts
2. **02-resource-design.md** - Designing REST resources
3. **03-jersey-setup.md** - Setting up Jersey framework
4. **04-crud-operations.md** - Implementing CRUD with Jersey
5. **05-versioning.md** - API versioning strategies
6. **06-jackson-basics.md** - JSON processing with Jackson
7. **07-jackson-advanced.md** - Advanced Jackson features
8. **08-security-basic.md** - Basic authentication
9. **09-security-jwt.md** - JWT implementation
10. **10-final-project.md** - Comprehensive exercise

## Running Presentations

### term_deck
```bash
cd /Users/tbecker/workspaces/term_deck
cargo run ../trainings/dbh-rest-with-jackson/slides/term_deck/01-rest-fundamentals.md

# Navigation
# h - previous slide
# l - next slide
# t - change theme
# q - quit
```

### presenterm
```bash
# Install if not already installed
cargo install presenterm

# Run presentation
presenterm slides/presenterm/01-rest-fundamentals.md

# With speaker notes (opens second window)
presenterm slides/presenterm/01-rest-fundamentals.md --presenter-mode

# Navigation
# → or Space - next slide
# ← - previous slide
# g - go to slide
# q - quit
```

## Creating New Slides

### term_deck Format
```markdown
---
title: Slide Title
author: Thomas Becker
subtitle: Module Name
---

## Slide Content

Your content here

<!-- end_slide -->
```

### presenterm Format
```markdown
---
title: Presentation Title
author: Thomas Becker
date: August 26, 2025
---

# Slide Title

Content

<!-- speaker_notes -->
Notes for the presenter
<!-- end_speaker_notes -->

---

# Next Slide
```

## Features Comparison

| Feature | term_deck | presenterm |
|---------|-----------|------------|
| Basic slides | ✅ | ✅ |
| Code syntax | ✅ | ✅ +line numbers |
| Images | ✅ PNG | ✅ PNG + GIF |
| Speaker notes | ❌ | ✅ |
| Columns | ❌ | ✅ |
| Pauses | ❌ | ✅ |
| Dynamic highlighting | ❌ | ✅ |
| Live reload | ❌ | ✅ |
| PDF export | ❌ | ✅ |

## Best Practices

1. **Keep slides concise** - Terminal space is limited
2. **Use code examples** - Both formats support syntax highlighting
3. **Add speaker notes** (presenterm) - Helps during delivery
4. **Progressive disclosure** - Use pauses in presenterm
5. **Test both formats** - Ensure compatibility
6. **Include timing** - Note duration for each module
7. **Mark exercises clearly** - Use visual indicators

## Converting Between Formats

To convert from term_deck to presenterm:
1. Replace `<!-- end_slide -->` with `---`
2. Add speaker notes sections
3. Add pauses for progressive content
4. Utilize column layouts where beneficial

To convert from presenterm to term_deck:
1. Replace `---` with `<!-- end_slide -->`
2. Remove speaker notes sections
3. Remove pauses and column layouts
4. Simplify to basic markdown

## Module Timing

| Module | Duration | Slides |
|--------|----------|--------|
| REST Fundamentals | 45 min | 18 |
| Resource Design | 30 min | 12 |
| Jersey Setup | 45 min | 15 |
| CRUD Operations | 60 min | 20 |
| Versioning | 30 min | 10 |
| Jackson Basics | 45 min | 15 |
| Jackson Advanced | 45 min | 15 |
| Security Basic | 30 min | 12 |
| Security JWT | 45 min | 18 |
| Final Project | 60 min | 8 |

Total: ~6.5 hours of content + exercises