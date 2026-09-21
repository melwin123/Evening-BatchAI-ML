# Day 1 - Java Quick Start + Agile

## Run
```bash
cd src
javac com/amc/bfsi/*.java
java com.amc.bfsi.Day01Demo
```

## Sprint 0 deliverable
1. Create the GitHub repo `bfsi-fsd-app`.
2. `git init`, add a `.gitignore` for Java, commit the Day 1 classes.
3. Create a project board with one column per sprint (Day 1 to Day 10).
4. Write the first three user stories:
   - As a bank officer I want to register a customer so that I can open accounts.
   - As a customer I want to view my accounts so that I can check my balance.
   - As a bank officer I want to sanction a loan against a customer.

## Git commands used in the session
```bash
git init
git add .
git commit -m "Day 1: customer and account domain classes"
git branch -M main
git remote add origin https://github.com/<you>/bfsi-fsd-app.git
git push -u origin main
```
