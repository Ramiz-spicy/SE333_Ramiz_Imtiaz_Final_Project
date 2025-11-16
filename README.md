# SE333 Final Project: Intelligent Testing and Security Agent

**Author:** Ramiz Imtiaz  
**Course:** SE333 – Software Testing & Analysis  
**Institution:** DePaul University  

---

## Project Overview

This project implements a fully autonomous AI software engineering agent using the Model Context Protocol (MCP).  
The agent automatically analyzes, edits, tests, and improves a Java Maven codebase through iterative self-evaluation.

The agent is capable of:

- Automated JUnit test generation  
- Iterative test improvement based on coverage gaps  
- Bug detection and automated repair  
- Boundary value test case creation  
- Java security vulnerability scanning  
- Static code review and cleanup  
- Full Git workflow automation  
- Pull request generation using GitHub CLI  

This project demonstrates an AI-driven, end-to-end software development loop designed to enhance code quality, maintainability, and correctness.

---

## Installation and Configuration

Follow all steps below to fully configure the environment and run the agent.

---

### 1. System Requirements

Install the following:

- Java JDK 11 or higher  
- Maven 3.6 or higher  
- Python 3.10 or higher  
- Node.js 18+  
- Git  
- GitHub CLI (gh)  
- VS Code (latest)  
- VS Code Chat view enabled  

---

### 2. Create and activate Python virtual environment

**Create venv:**

```bash
uv venv
```

**macOS / Linux:**

```bash
source .venv/bin/activate
```

**Windows:**

```bash
.venv\Scripts\activate
```

---

### 3. Install dependencies

```bash
uv add mcp[cli] httpx fastmcp
```

---

### 4. Run the MCP server

```bash
python server.py
```

---

### 5. Connect MCP server to VS Code

- Open Command Palette (Ctrl+Shift+P)  
- Select: MCP: Add Server  
- Enter server URL:  
  ```
  http://127.0.0.1:8000
  ```  
- Assign a server name  

---

### 6. Enable Auto-Approve

- Open Command Palette  
- Select: Chat: Settings  
- Enable Auto-Approve  

---

## MCP Tool API Documentation

Below is the complete list of AI tools used by the agent.

---

### Core Test and Analysis Tools (Phase 2 & 4)

| Tool | Description | Purpose |
|------|-------------|---------|
| run_maven_test | Runs `mvn clean verify` | Executes full build and tests |
| get_quality_dashboard | Parses Surefire and JaCoCo reports | Shows coverage and test metrics |
| get_test_failures | Extracts Surefire failures | Identifies test errors |
| get_missing_coverage | Reads JaCoCo XML | Finds untested classes and lines |
| read_file_content | Reads a file | Used for analysis |
| write_file_content | Writes to a file | Used for fixes and new test generation |

---

### Git Automation Tools (Phase 3)

| Tool | Purpose |
|------|---------|
| git_status | Show modified files |
| git_add_all | Stage all changes |
| git_commit | Commit staged changes |
| git_push | Push current branch |
| git_pull_request | Create PR using GitHub CLI |

---

### Advanced Extensions (Phase 5)

| Tool | Description |
|------|-------------|
| generate_bva_test_cases | Produces boundary-value test cases based on input constraints |
| run_pmd_analysis | Runs static code review using PMD and extracts violations |

---

## Achievements in the Codebase

The agent successfully improved and repaired a real large-scale Java Maven project:

### Build Fix
Resolved a conflict in `pom.xml` between the JaCoCo agent and Maven Surefire that prevented coverage reporting.

### Bug Fix: NullPointerException
Located and fixed an NPE in `SystemUtils.java` related to Java version parsing. This repair restored stability across dozens of failing tests.

### Reflection / JDK17 Fix
Added required `--add-opens` flags to resolve `InaccessibleObjectException` errors caused by strong encapsulation in modern JDKs.

### Security & Static Analysis
Executed PMD static analysis and identified more than 98 violations, demonstrating automated code review capability.

### Full Git Automation
The agent staged, committed, pushed, and opened pull request branches automatically.

---

## How to Run the AI Agent

In VS Code Chat, run the following command:

```
Run the build and show me the Quality Dashboard.
```

The agent will:

1. Run Maven build and tests  
2. Analyze JaCoCo coverage  
3. Identify failing tests  
4. Read code and generate fixes  
5. Add and improve tests  
6. Commit changes  
7. Push to branch  
8. Create PR (if GitHub CLI installed)  

This creates a full iterative AI-driven cycle that continually improves the software.

---

## Completed Project Requirements

- Fully operational MCP server  
- Test-generation agent with coverage feedback loop  
- Security scanning extension  
- Boundary value test generator  
- Git automation suite  
- Comprehensive README documentation  

---

## Troubleshooting and FAQ

**Server cannot start**  
Check Python version (must be 3.10+).  

**PR creation fails**  
Install GitHub CLI:  
```
winget install GitHub.cli
```

**MCP tools not detected**  
Ensure server.py is in the same directory as mcp.json.

**Coverage not generated**  
Ensure JaCoCo plugin exists in `pom.xml`.

---

## End of README
