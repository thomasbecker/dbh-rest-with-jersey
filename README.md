# DBH REST API Training with Jersey - Main Development Repository

## Overview

This is the main development repository for the DBH REST API training using
Jersey and Jackson. This repository contains ALL training materials including
participant exercises, instructor solutions, showcases, and slides.

## 🏗️ Repository Structure

```
dbh-rest-with-jackson/              # Main development repo (THIS REPO)
├── participant/                     # Materials for participants
│   ├── base-project/               # Starting Jersey template
│   ├── exercises/                  # Progressive hands-on exercises
│   └── README.md                   # Participant instructions
├── instructor/                      # Instructor-only materials
│   ├── solutions/                  # Complete exercise solutions
│   ├── showcases/                  # Advanced demos
│   └── guide/                      # Teaching notes and timing
├── slides/                         # Presentation materials (term_deck)
├── scripts/                        # Distribution automation
│   ├── create-participant-repo.sh  # Deploy participant materials
│   ├── create-solution-repo.sh     # Deploy solutions (private)
│   └── sync-repos.sh              # Sync all distribution repos
├── agenda.md                       # 2-day training schedule
├── PLAN.md                         # Detailed preparation plan
└── .github/workflows/              # CI/CD automation
```

## 📦 Distribution Strategy

This repository uses a **hybrid approach** for distribution:

### Development (This Repo)
- All development happens here
- Complete materials in one place
- Version controlled together

### Distribution Repos (Auto-generated)
1. **`dbh-jersey-training-participant`** (Public)
   - Contains: `base-project/`, `exercises/`, setup scripts
   - For: Training participants
   
2. **`dbh-jersey-training-solutions`** (Private)
   - Contains: Complete solutions for all exercises
   - For: Instructors only

3. **`dbh-jersey-training-slides`** (Public/Private as needed)
   - Contains: Presentation materials
   - For: Instructors and possibly participants

## 🚀 Quick Start

### For Development

1. **Clone this repository:**
   ```bash
   git clone <this-repo-url>
   cd dbh-rest-with-jackson
   ```

2. **Develop materials:**
   - Participant exercises in `participant/exercises/`
   - Solutions in `instructor/solutions/`
   - Slides in `slides/`

3. **Deploy to distribution repos:**
   ```bash
   ./scripts/sync-repos.sh
   ```

### For Training Delivery

Participants should clone:
```bash
git clone https://github.com/dbh-training/dbh-jersey-training-participant
```

Instructors should additionally clone:
```bash
git clone https://github.com/dbh-training/dbh-jersey-training-solutions
```

## 🔧 Technical Requirements

### Java 8 Compatibility (Critical)
- **Java Version**: 8 (not 11 or 21)
- **Jersey**: 2.35 (not 3.x)
- **Jackson**: 2.14.x
- **Build Tool**: Gradle 8.5+

### Development Tools
- IntelliJ IDEA or similar IDE
- Git
- Docker (for showcases)
- term_deck (for slides)

## 📚 Training Content

### Day 1: REST Fundamentals & Jersey
1. REST principles and maturity models
2. Resource design patterns
3. Jersey CRUD operations
4. API versioning strategies

### Day 2: Jackson & Security
5. Jackson JSON processing basics
6. Advanced Jackson features
7. Basic authentication
8. JWT implementation
9. API key management
10. Final comprehensive project

## 🛠️ Development Workflow

1. **Make changes** in this main repository
2. **Test locally** to ensure everything works
3. **Run sync script** to update distribution repos:
   ```bash
   ./scripts/sync-repos.sh
   ```
4. **Verify** distribution repos are updated correctly
5. **Push** distribution repos if not using GitHub Actions

## 🤖 Automation

GitHub Actions automatically syncs distribution repositories when:
- Changes are pushed to `main` branch
- Participant or instructor materials are modified
- Manual trigger via GitHub UI

Required secrets:
- `DISTRIBUTION_TOKEN`: GitHub PAT with repo access

## 📝 Important Notes

1. **Participant Repository**: Must be PUBLIC
2. **Solutions Repository**: Must be PRIVATE
3. **Java 8**: All code must be Java 8 compatible
4. **No Spring Boot**: Pure Jersey implementation
5. **Progressive Exercises**: Each builds on previous

## 🎯 Success Criteria

- [x] Clear separation of participant/instructor materials
- [x] Automated distribution to separate repos
- [x] Java 8 compatibility throughout
- [ ] All 10 exercises with solutions
- [ ] Complete instructor guide
- [ ] Tested showcase demos
- [ ] Term_deck slides ready

## 📞 Contact

- Training Date: August 26, 2025
- Customer: DBH
- Focus: Jersey (no Spring Boot), Java 8 environment

---

**For questions about this training, see [PLAN.md](PLAN.md) for detailed
preparation information.**