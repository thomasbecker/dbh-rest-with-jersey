#!/bin/bash

# Script to create/update participant repository from main development repo
# This copies only participant-relevant materials to a clean distribution repo

set -e

# Configuration
MAIN_REPO_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
PARTICIPANT_REPO_NAME="dbh-jersey-training-participant"
PARTICIPANT_REPO_PATH="${MAIN_REPO_PATH}/../${PARTICIPANT_REPO_NAME}"

echo "Creating participant repository from: ${MAIN_REPO_PATH}"
echo "Target repository: ${PARTICIPANT_REPO_PATH}"

# Create target directory if it doesn't exist
if [ ! -d "${PARTICIPANT_REPO_PATH}" ]; then
    echo "Creating participant repository directory..."
    mkdir -p "${PARTICIPANT_REPO_PATH}"
    cd "${PARTICIPANT_REPO_PATH}"
    git init
    git remote add origin "git@github.com:dbh-training/${PARTICIPANT_REPO_NAME}.git" 2>/dev/null || true
else
    echo "Participant repository already exists, updating..."
fi

# Copy participant materials
echo "Copying participant materials..."
cp -r "${MAIN_REPO_PATH}/participant/base-project" "${PARTICIPANT_REPO_PATH}/" 2>/dev/null || true
cp -r "${MAIN_REPO_PATH}/participant/exercises" "${PARTICIPANT_REPO_PATH}/" 2>/dev/null || true
cp "${MAIN_REPO_PATH}/participant/README.md" "${PARTICIPANT_REPO_PATH}/README.md"

# Create a setup script for participants
cat > "${PARTICIPANT_REPO_PATH}/setup.sh" << 'EOF'
#!/bin/bash
# Quick setup script for participants

echo "Setting up DBH REST Training environment..."

# Check Java version
java_version=$(java -version 2>&1 | head -n 1 | cut -d'"' -f 2 | cut -d'.' -f 1-2)
if [[ "$java_version" != "1.8" ]]; then
    echo "Warning: Java 8 is required. Found: $java_version"
    echo "Please install Java 8 before continuing."
    exit 1
fi

# Build base project
cd base-project
./gradlew build

if [ $? -eq 0 ]; then
    echo "Setup complete! Base project built successfully."
    echo "To start the server: java -jar build/libs/rest-training-1.0.jar"
else
    echo "Build failed. Please check the error messages above."
    exit 1
fi
EOF

chmod +x "${PARTICIPANT_REPO_PATH}/setup.sh"

# Create .gitignore
cat > "${PARTICIPANT_REPO_PATH}/.gitignore" << 'EOF'
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
gradle-app.setting
!gradle-wrapper.jar

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
cd "${PARTICIPANT_REPO_PATH}"
if [ -d .git ]; then
    git add -A
    git commit -m "Update participant materials from main repo" || echo "No changes to commit"
    echo "Participant repository updated successfully!"
    echo ""
    echo "To push to GitHub:"
    echo "  cd ${PARTICIPANT_REPO_PATH}"
    echo "  git push origin main"
else
    echo "Participant materials copied. Initialize git and push when ready."
fi

echo ""
echo "Participant repository ready at: ${PARTICIPANT_REPO_PATH}"