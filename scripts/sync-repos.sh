#!/bin/bash

# Master sync script to update all distribution repositories
# Run this after making changes in the main development repo

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

echo "==============================================="
echo "Syncing DBH REST Training Distribution Repos"
echo "==============================================="
echo ""

# Sync participant repository
echo "1. Syncing participant repository..."
echo "-----------------------------------"
"${SCRIPT_DIR}/create-participant-repo.sh"
echo ""

# Sync solutions repository
echo "2. Syncing solutions repository..."
echo "-----------------------------------"
"${SCRIPT_DIR}/create-solution-repo.sh"
echo ""

# Sync slides if script exists
if [ -f "${SCRIPT_DIR}/create-slides-repo.sh" ]; then
    echo "3. Syncing slides repository..."
    echo "-----------------------------------"
    "${SCRIPT_DIR}/create-slides-repo.sh"
    echo ""
fi

echo "==============================================="
echo "All repositories synced successfully!"
echo "==============================================="
echo ""
echo "Next steps:"
echo "1. Review the changes in each distribution repo"
echo "2. Push to GitHub as needed"
echo "3. Verify participant repo is public"
echo "4. Verify solutions repo is private"