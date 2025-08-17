# Presenterm Speaker Notes Guide

## Correct Syntax

### Single Line Speaker Notes
```markdown
<!-- speaker_note: This is a single line note -->
```

### Multiline Speaker Notes
```markdown
<!-- 
speaker_note: |
  This is a multiline speaker note.
  It uses YAML-style pipe notation.
  Each line must be indented consistently.
  - Bullet points work
  - Multiple paragraphs too
-->
```

## Running with Speaker Notes

### Method 1: Two Terminal Windows

**Terminal 1 - Main Presentation (Presenter's screen):**
```bash
presenterm slides.md --publish-speaker-notes
```

**Terminal 2 - Speaker Notes (Presenter's laptop):**
```bash
presenterm slides.md --listen-speaker-notes
```

### Method 2: Configure Auto-Publish

Create `~/.config/presenterm/config.yaml`:
```yaml
speaker_notes:
  always_publish: true
```

Then just run:
```bash
# Terminal 1
presenterm slides.md

# Terminal 2
presenterm slides.md --listen-speaker-notes
```

## Platform Notes

- **Linux/Windows**: Supports multiple listener instances
- **macOS**: Only one listener instance supported
- Uses UDP sockets for communication between instances

## Best Practices

1. **Keep notes concise** - You'll be reading while presenting
2. **Use bullet points** - Easier to scan quickly
3. **Include timing reminders** - "5 minutes for this section"
4. **Add interaction prompts** - "Ask about experience with X"
5. **Note difficult concepts** - Reminders for extra explanation
6. **Include transition phrases** - Smooth flow between topics

## Example Slide with Notes

```markdown
---
title: REST API Training
author: Thomas Becker
---

# HTTP Methods in REST

```http
GET     - Retrieve resources
POST    - Create new resources
PUT     - Update/Replace resources
DELETE  - Remove resources
```

<!-- speaker_note: Emphasize idempotency of GET, PUT, DELETE -->

<!-- pause -->

### Safe vs Idempotent

- **Safe**: No server state change
- **Idempotent**: Same result on multiple calls

<!-- 
speaker_note: |
  Important distinctions:
  - Safe methods: GET, HEAD, OPTIONS
  - Idempotent: GET, PUT, DELETE  
  - POST is neither safe nor idempotent
  - This is often misunderstood
  - Give real-world examples
-->
```

## Troubleshooting

### Notes Not Showing
- Ensure both instances are running
- Check firewall isn't blocking UDP
- Verify correct syntax (speaker_note, not speaker_notes)

### Connection Issues
- Both instances must be on same machine
- Try specifying port: `--publish-speaker-notes-port 9876`

### Formatting Issues
- YAML indentation must be consistent
- Use spaces, not tabs
- Close multiline comments properly

## Migration from Incorrect Syntax

**Wrong (old):**
```markdown
<!-- speaker_notes -->
Note content
<!-- end_speaker_notes -->
```

**Correct:**
```markdown
<!-- speaker_note: Note content -->

<!-- Or multiline -->
<!-- 
speaker_note: |
  Note content
  More content
-->
```

## Quick Reference

| Syntax | Use Case |
|--------|----------|
| `<!-- speaker_note: text -->` | Single line notes |
| `<!-- speaker_note: \| -->` | Multiline notes with YAML |
| `--publish-speaker-notes` | Start publisher instance |
| `--listen-speaker-notes` | Start listener instance |

## Testing Your Setup

1. Create a test slide:
```markdown
---
title: Test
---

# Test Slide

Content

<!-- speaker_note: This is a test note -->
```

2. Run publisher:
```bash
presenterm test.md --publish-speaker-notes
```

3. Run listener (new terminal):
```bash
presenterm test.md --listen-speaker-notes
```

4. Navigate slides and verify notes appear in listener window