# Pathify Contribution Guide

## Clone
   ```bash
   git clone https://github.com/prthm412/Pathify.git
   ```

## Branching
- Always branch from `main`.
- Branch names: `feat/<feature-name>`, `fix/<bug-name>`, `chore/<misc>`.

## Workflow
1. **Update local main**
   ```bash
   git fetch origin
   git switch main
   git pull --ff-only origin main

2. **Create feature branch
   ```bash
   git switch -c feat/<feature-name>

3. Push branch & open PR
   ```bash
   git push -u origin feat/<feature-name>

4. Rebase
   ```bash
   git fetch origin
   git rebase origin/main
   git push --force-with-lease

Reviews
- Every PR must be reviewed by at least one teammate
- No direct commits to 'main'

Commits
- Use clear prefixes
  - feat: for new features
  - fix: for bug fixes
  - chore: for setup, config, docs
  - refactor: for restructuring code
