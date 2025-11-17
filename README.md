# SE333 Final Project: Intelligent Testing and Security Agent

**Author:** Ramiz Imtiaz  
**Course:** SE333 – Software Testing & Analysis  
**Institution:** DePaul University  
link: https://github.com/Ramiz-spicy/SE333_Ramiz_Imtiaz_Final_Project
---

## Project Overview

This project implements a fully autonomous AI software engineering agent using the Model Context Protocol (MCP).  
The agent automatically analyzes, edits, tests, and improves a Java Maven codebase through iterative self-evaluation.

The agent supports:

- Automated JUnit test generation  
- Iterative test improvement based on coverage  
- Bug detection and automated repairs  
- Boundary value, equivalence class, and decision table test generation  
- Java security vulnerability scanning  
- Static analysis with autofix suggestions  
- Dependency CVE scanning  
- Full Git automation (commit, push, PR creation)  

---

## Installation and Configuration

### 1. System Requirements

- Java JDK 11+  
- Maven 3.6+  
- Python 3.10+  
- Node.js 18+  
- Git + GitHub account  
- GitHub CLI (`gh`)  
- VS Code + Chat view enabled  

### 2. Create Python venv

```bash
uv venv
```

macOS/Linux:

```bash
source .venv/bin/activate
```

Windows:

```bash
.venv\Scripts\activate
```

### 3. Install dependencies

```bash
uv add mcp[cli] httpx fastmcp
```

### 4. Run MCP Server

```bash
python server.py
```

### 5. Connect to VS Code

- Ctrl+Shift+P → “MCP: Add Server”  
- Enter: `http://127.0.0.1:8000`  
- Assign name  

### 6. Enable Auto-Approve

- Ctrl+Shift+P → Chat: Settings → Enable Auto-Approve  

---

## MCP Tool API Documentation

Below is the complete tool suite implemented across all project phases.

---

## Core Test and Analysis Tools

| Tool | Description |
|------|-------------|
| run_maven_test | Runs `mvn clean verify` |
| get_quality_dashboard | Reads Surefire + JaCoCo reports |
| get_test_failures | Extracts test failure stack traces |
| get_missing_coverage | Identifies untested lines/classes |
| read_file_content | Reads any file |
| write_file_content | Writes updated content |

---

## Git Automation Tools

| Tool | Purpose |
|------|---------|
| git_status | Shows modified files |
| git_add_all | Stages all changes |
| git_commit | Commits staged content |
| git_push | Pushes current branch |
| git_pull_request | Creates GitHub pull request |

---

# Phase 5: Advanced Security Extensions  
(All of the following were implemented and fully functional)

---

## 1. Specification-Based Test Generator  
**File:** spec_testgen.py  
**Tools implemented:**

### `boundary_tests(method_signature, output_path)`
- Extracts parameter types from a method signature  
- Generates boundary test values  
- Produces a JUnit test file  

### `equivalence_tests(method_signature, output_path)`
- Generates representative equivalence class tests  
- Handles int, Integer, String, and general object inputs  

### `decision_table_tests(java_file, output_path)`
- Extracts condition expressions from Java source  
- Builds truth-table combinations  
- Outputs JUnit tests for each rule  

---

## 2. Advanced Security Analyzer  
**File:** mcp_advanced_security.py  
This module performs:

### Pattern-based security detection:
- SQL Injection (`executeQuery`, `prepareStatement` with concatenation)  
- Java deserialization  
- Runtime command execution  
- MD5 / SHA-1 weak hashing  
- Insecure Random() usage  
- Reflected input  
- Hard-coded credentials  
- ClassLoader RCE  
- Path traversal patterns  

### Taint Analysis:
Tracks data originating from:

```
request.getParameter("<name>")
```

Then detects whether tainted input flows into:

- SQL queries  
- File paths  
- exec() commands  

### Auto-fix Suggestions:
- Replace MD5 with SHA-256  
- Replace Random() with SecureRandom  
- Use PreparedStatement placeholders  

**Tool:**  
`analyze_java(file_path)` → returns security_issues + autofix_suggestions  

---

## 3. Vulnerability Intelligence Tool  
**File:** mcp_vulnerability_intel.py  
This tool scans Maven dependencies for known vulnerabilities.

### Features:
- Reads Maven dependency tree (`mvn dependency:tree -DoutputType=json`)  
- Checks each library against an embedded CVE mini-database  
- Computes project CVSS-based risk score  
- Recommends safe versions  

### Example detections:
- log4j 2.14.0 → CVE-2021-44228 (CVSS 10.0)  
- commons-lang3 3.1 → CVE-2017-12345  

**Tool:**  
`vulnerability_scan(project_path)`  

Returns:
- vulnerabilities (artifact, version, CVEs, CVSS)  
- risk_score  
- overall_risk (low, medium, high, critical)

---

# Achievements in the Codebase

### Build Fix
Resolved JaCoCo + Surefire conflict preventing coverage generation.

### NullPointerException Fix
Corrected an NPE in SystemUtils.java causing widespread test failures.

### JDK 17 Reflection Fix
Added required `--add-opens` flags to fix InaccessibleObjectException.

### PMD Static Analysis
Identified 98+ code smells and violations.

### Full Git Automation
Agent created branches, staged changes, committed fixes, pushed updates, and generated PRs.

---

# How to Run the Autonomous Agent

In VS Code Chat, run:

```
Run the build and show me the Quality Dashboard.
```

The agent will:

1. Build the project  
2. Analyze failures  
3. Examine coverage  
4. Add tests  
5. Fix bugs  
6. Improve security  
7. Commit + push  
8. Create PR  

---

# Troubleshooting

**PR command fails:**  
Install GitHub CLI:  
```
winget install GitHub.cli
```

**MCP tools not showing:**  
Ensure server.py is in same directory as mcp.json.

**Coverage reports missing:**  
Verify JaCoCo plugin exists in pom.xml.

---

# End of README

