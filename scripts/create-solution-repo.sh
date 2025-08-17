#!/bin/bash

# Script to create/update solutions repository from main development repo
# This copies instructor solutions to a private distribution repo

set -e

# Configuration
MAIN_REPO_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
SOLUTION_REPO_NAME="dbh-jersey-training-solutions"
SOLUTION_REPO_PATH="${MAIN_REPO_PATH}/../${SOLUTION_REPO_NAME}"

echo "Creating solutions repository from: ${MAIN_REPO_PATH}"
echo "Target repository: ${SOLUTION_REPO_PATH}"

# Create target directory if it doesn't exist
if [ ! -d "${SOLUTION_REPO_PATH}" ]; then
    echo "Creating solutions repository directory..."
    mkdir -p "${SOLUTION_REPO_PATH}"
    cd "${SOLUTION_REPO_PATH}"
    git init
    git remote add origin "git@github.com:dbh-training/${SOLUTION_REPO_NAME}.git" 2>/dev/null || true
else
    echo "Solutions repository already exists, updating..."
fi

# Copy solution materials
echo "Copying solution materials..."
cp -r "${MAIN_REPO_PATH}/instructor/solutions/"* "${SOLUTION_REPO_PATH}/" 2>/dev/null || true

# Create README for solutions repo
cat > "${SOLUTION_REPO_PATH}/README.md" << 'EOF'
# DBH REST Training - Solutions

## ⚠️ INSTRUCTOR USE ONLY

This repository contains complete solutions for all exercises in the DBH REST
API training with Jersey and Jackson.

## Repository Structure

```
solutions/
├── 01-rest-basics-solution/
├── 02-resource-design-solution/
├── 03-jersey-crud-solution/
├── 04-versioning-solution/
├── 05-jackson-basics-solution/
├── 06-jackson-advanced-solution/
├── 07-security-basic-solution/
├── 08-security-jwt-solution/
├── 09-security-apikey-solution/
└── 10-final-project-solution/
```

## Usage Guidelines

1. **Reference Only**: Use these solutions as reference during training
2. **Don't Share Directly**: Guide participants to find solutions themselves
3. **Alternative Approaches**: Each solution may have comments about alternatives
4. **Common Pitfalls**: Solutions include notes about common mistakes

## Java 8 Compatibility

All solutions are tested with:
- Java 8
- Jersey 2.35
- Jackson 2.14.x

## Testing Solutions

Each solution includes:
- Complete working code
- Unit and integration tests
- README with explanation

To test any solution:
```bash
cd XX-exercise-solution
./gradlew test
./gradlew run
```

## Notes for Instructors

- Solutions demonstrate best practices
- Include error handling and validation
- Show production-ready patterns
- Comment complex sections

---

**CONFIDENTIAL - For instructor use only**
EOF

# Create .gitignore
cat > "${SOLUTION_REPO_PATH}/.gitignore" << 'EOF'
# Build outputs
build/
target/
out/
*.class
*.jar
*.war

# IDE files
.idea/
*.iml
*.ipr
*.iws
.vscode/
.classpath
.project
.settings/

# Gradle
.gradle/

# OS files
.DS_Store
Thumbs.db

# Logs
*.log

# Temp files
*~
*.swp
*.swo
*.tmp
EOF

# Commit changes if in git repo
cd "${SOLUTION_REPO_PATH}"
if [ -d .git ]; then
    git add -A
    git commit -m "Update solutions from main repo" || echo "No changes to commit"
    echo "Solutions repository updated successfully!"
    echo ""
    echo "To push to GitHub (ensure repo is private!):"
    echo "  cd ${SOLUTION_REPO_PATH}"
    echo "  git push origin main"
else
    echo "Solution materials copied. Initialize git and push when ready."
fi

echo ""
echo "Solutions repository ready at: ${SOLUTION_REPO_PATH}"
echo "⚠️  Remember: This repository must remain PRIVATE"