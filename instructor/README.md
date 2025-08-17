# DBH REST Training - Instructor Guide

## Overview

This directory contains instructor-only materials for the DBH REST API training
with Jersey and Jackson.

## Directory Structure

```
instructor/
├── solutions/      # Complete solutions for all exercises
├── showcases/      # Advanced demos for instructor presentation
│   ├── client-certificates/
│   ├── openapi/
│   └── docker/
└── guide/          # Teaching notes and timing guides
```

## Quick Links

- [Training Agenda](../agenda.md)
- [Detailed Plan](../PLAN.md)
- [Participant Materials](../participant/)
- [Slides](../slides/)

## Solutions Organization

Each solution folder mirrors the exercise structure:
- Complete, working implementation
- Additional comments explaining key concepts
- Alternative approaches where applicable
- Common pitfalls and how to address them

## Showcases

These are demonstration-only materials not meant for participant exercises:

### Client Certificates
- Mutual TLS setup
- Certificate generation
- Jersey configuration
- Testing with curl

### OpenAPI/Swagger
- API documentation generation
- Swagger UI integration
- Live API testing

### Docker Deployment
- Containerization of Jersey applications
- Docker Compose with databases
- Basic Kubernetes examples

## Teaching Notes

See the `guide/` directory for:
- Detailed timing for each module
- Common questions and answers
- Troubleshooting guide
- Alternative explanations for difficult concepts
- Tips for different skill levels

## Preparation Checklist

Before training:
- [ ] Review all exercise solutions
- [ ] Test showcase demos
- [ ] Verify participant setup instructions
- [ ] Prepare backup materials for technical issues
- [ ] Review common questions in guide

## During Training

- Use solutions as reference, not for direct sharing
- Showcases are for demonstration only
- Encourage participants to struggle before hints
- Adapt pace based on group progress

## Java 8 Compatibility

All materials are Java 8 compatible. Key constraints:
- Jersey 2.35 (not 3.x)
- Jackson 2.14.x
- No Java 9+ features (modules, var, etc.)

---

**For instructor use only - Do not share with participants**