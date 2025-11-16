SE333 Final Project: Intelligent Testing and Security Agent

Ramiz Imtiaz
DePaul University – SE333: Software Testing & Analysis

Overview

This project implements a fully autonomous AI software engineering agent built using the Model Context Protocol (MCP). The agent is capable of analyzing, modifying, testing, and securing a Java Maven codebase without direct human intervention. It automates:

Test generation

Iterative test improvement

Bug detection and repair

Coverage expansion through JaCoCo

Security vulnerability scanning

Boundary value test case generation

Git workflows including committing, pushing, and PR creation

This project demonstrates an end-to-end AI-driven development loop, where the software agent functions as an intelligent collaborator capable of independently improving software quality.

Installation and Configuration

Follow all steps carefully to run the intelligent MCP agent.

Prerequisites

You must install the following:

Required System Tools

Java JDK 11+

Apache Maven 3.6+

Python 3.10+

Node.js 18+

Git

GitHub account with push access

GitHub CLI (gh) if using automated pull-request creation

Required VS Code Environment

Visual Studio Code (latest)

VS Code Chat view enabled

MCP support active in VS Code

Project Setup
1. Clone the Project
git clone https://github.com/Ramiz-spicy/SE333_Ramiz_Imtiaz_Final_Project.git
cd SE333_Ramiz_Imtiaz_Final_Project

2. Set Up Python Virtual Environment

Windows:

python -m venv venv
venv\Scripts\activate


macOS/Linux:

python3 -m venv venv
source venv/bin/activate

3. Install Dependencies
pip install -r requirements.txt

4. Configure VS Code MCP Server

Open:

%APPDATA%\Code\User\mcp.json


Use this configuration:

{
  "servers": [
    {
      "type": "stdio",
      "command": "python",
      "args": ["mcp_server.py"],
      "name": "Ultimate Agent Server",
      "cwd": "C:\\Users\\Administrator\\se333-mcp\\extensions"
    }
  ],
  "inputs": [
    {
      "type": "prompt",
      "path": ".github/prompts/tester.prompt.md"
    }
  ]
}


Restart VS Code.
The server should appear as Ultimate Agent Server under MCP Servers.

Running the Intelligent Agent

Inside VS Code Chat, run:

Improve the tests and show me the updated coverage report.


or:

Run the build and show me the Quality Dashboard.


The agent will automatically:

Parse Java source files

Generate missing tests

Improve existing tests

Expand branch and line coverage

Run Maven with JaCoCo

Detect failures

Repair bugs by editing Java source

Commit, push, and optionally create a PR

This forms a complete automated development cycle.

MCP Tool API Documentation

Below are the tools implemented in the project, categorized by the project requirements.

Phase 2 & Phase 4 Tools: Core Testing and Iteration
parse_java

Extracts all method names/signatures from a Java class.
Used to drive automated test generation.

generate_tests

Creates JUnit test skeletons for every discovered method.

run_tests

Runs the full Maven pipeline:

mvn clean test jacoco:report


Also produces Surefire and JaCoCo XML/HTML reports.

read_file_content

Reads any source file so the agent can analyze code and propose modifications.

write_file_content

Writes Java source, test files, or configuration updates.

generate_boundary_tests

Implements Boundary Value Analysis (BVA) by generating min/zero/max values for integer constraints.

security_scan_java

Scans Java files for:

SQL injection

Hard-coded credentials

Command injection

Unsafe string concatenation

Phase 3: Git Automation Tools
git_status

Returns all modified and uncommitted changes.

git_add_all

Stages all file changes.

git_commit

Commits changes using a standardized commit message.

git_push

Pushes local commits to the configured remote branch.

git_pull_request

Creates a pull request using the GitHub CLI.
If gh is not installed, the agent automatically:

Creates a branch

Pushes the fix

Returns a URL for manual PR creation

Phase 5: Creative Extensions
Extension 1 — Boundary Value Test Generator

Automatically generates BVA test cases for integer-based methods.
Useful for improving coverage of edge cases.

Extension 2 — Security Review Agent

Automatically detects security vulnerabilities using static analysis patterns.
Findings include:

SQL injection

Command injection

Hard-coded secrets

Unsafe input usage

Missing validation

Intelligent Test Iteration Workflow (Phase 4)

The agent performs multi-step iterations until reaching stable, high-coverage test suites:

Parse Java classes

Detect missing tests

Generate initial tests

Run the build

Analyze JaCoCo coverage

Add more tests

Detect bugs exposed by tests

Repair Java source

Rebuild and re-test

Repeat until no further improvements are possible

This satisfies the Intelligent Test Iteration requirement.

Quality Dashboard

The agent collects metrics from:

target/site/jacoco/index.html

target/surefire-reports/*.xml

Tracked metrics:

Total tests

Passing/failing tests

Coverage percentages

Missed instructions

Missed branches

Uncovered classes

Edge-case coverage completeness

Bugs Found and Fixed by the Agent

During the development of this project, the AI agent successfully identified and fixed defects in the Java codebase.

NullPointerException Fix

A bug inside Java version handling logic caused runtime NPEs.
The agent added defensive null checks and validated input assumptions.

Reflection Permission Fix

The agent identified failures caused by modern JDK module restrictions and automatically updated Maven configuration with the necessary:

--add-opens


arguments.

Test Failure Resolution

Dozens of brittle tests were automatically fixed by regenerating them using improved logic.

Troubleshooting & FAQ
Issue: MCP server exits with “cannot open file”

Fix: Ensure cwd in mcp.json correctly points to:

C:\Users\Administrator\se333-mcp\extensions

Issue: PR creation fails

Fix:

winget install GitHub.cli
gh auth login

Issue: Server tools not discovered

Fix: Ensure mcp_server.py ends with:

if __name__ == "__main__":
    mcp.run()

Issue: JaCoCo report missing

Fix:

cd codebase
mvn clean test jacoco:report
