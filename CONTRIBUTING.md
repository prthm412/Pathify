# Pathify Contribution Guide

## Branching
- Always branch from `main`.
- Branch names: `feat/<feature-name>`, `fix/<bug-name>`, `chore/<misc>`.

## Workflow
1. **Update local main**
   ```bash
   git fetch origin
   git switch main
   git pull --ff-only origin main
