SE333 Final Project: Intelligent Testing and Security Agent

Author: Ramiz Imtiaz
Course: SE333 – Software Testing & Analysis
Institution: DePaul University

Project Overview

This project implements a fully autonomous AI software engineering agent using the Model Context Protocol (MCP).
The agent autonomously analyzes, edits, tests, and improves a Java Maven codebase, handling:

Automated JUnit test generation

Iterative test improvement based on coverage

Bug detection and repair

Boundary value test case creation

Java security vulnerability scanning

Full Git workflow automation

Pull request generation through GitHub CLI

The project demonstrates an AI-driven, end-to-end software development loop designed to enhance code quality and reliability.

Installation and Configuration

Follow the steps below to fully configure and run the agent.

Prerequisites

Install all required tools:

System Requirements

Java JDK 11 or higher

Maven 3.6 or higher

Python 3.10 or higher

Node.js 18+

Git

GitHub account

GitHub CLI (gh) if using automated PR creation

VS Code Requirements

VS Code (latest version)

VS Code Chat view enabled

MCP support enabled

Project Setup
1. Clone the Repository
git clone https://github.com/Ramiz-spicy/SE333_Ramiz_Imtiaz_Final_Project.git
cd SE333_Ramiz_Imtiaz_Final_Project

2. Set Up Python Virtual Environment
Windows
python -m venv venv
venv\Scripts\activate

macOS/Linux
python3 -m venv venv
source venv/bin/activate

3. Install Python Dependencies
pip install -r requirements.txt

4. Configure the MCP Server in VS Code

Open the MCP config file:

%APPDATA%\Code\User\mcp.json


Use the following configuration:

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
The server should appear under MCP Servers as Ultimate Agent Server.

Running the Agent

Inside VS Code Chat, initiate a full test iteration:

Improve the tests and show me the updated coverage report.


or

Run the build and show me the Quality Dashboard.


The agent will automatically:

Parse Java classes

Generate missing test cases

Improve existing tests

Run Maven tests and JaCoCo

Detect failures

Fix bugs

Regenerate tests

Commit and push updates

Optionally create a pull request

MCP Tool API Documentation

All tools implemented for testing, iteration, analysis, and Git automation.

Phase 2 & Phase 4 Tools: Core Analysis and Iteration
parse_java

Extracts Java method signatures for test generation.

generate_tests

Creates JUnit test skeletons for identified methods.

run_tests

Executes:

mvn clean test jacoco:report


Generates Surefire XML and JaCoCo test coverage reports.

read_file_content

Reads any project file for analysis or debugging.

write_file_content

Writes test files, Java source code, or configuration changes.

generate_boundary_tests

Generates boundary value test cases using integer min/zero/max logic.

security_scan_java

Detects:

SQL injection

Command injection

Hard-coded credentials

Unsafe parameter concatenation

Phase 3 Tools: Git Automation
git_status

Returns all uncommitted changes.

git_add_all

Stages all modifications.

git_commit

Commits changes using a standardized message.

git_push

Pushes the current branch to the remote repository.

git_pull_request

Creates a pull request using GitHub CLI.
If the CLI is unavailable, the agent:

Creates a new branch

Pushes changes

Returns a GitHub URL for manual PR creation

Phase 5 Tools: Creative Extensions
generate_bva_test_cases

Implements Boundary Value Analysis for integer-based constraints.

security_review_agent

Scans Java code for:

Injection vulnerabilities

Hard-coded secrets

Unsafe patterns

Missing validation

Intelligent Test Iteration (Phase 4)

The agent runs a multi-step automated testing loop:

Parse source code

Detect missing tests

Generate or improve tests

Run Maven build

Analyze coverage

Add more tests

Detect and fix exposed bugs

Re-run analysis

Repeat until coverage stabilizes

This fully satisfies the Intelligent Test Iteration requirement of the final project.

Quality Dashboard

The agent analyzes:

JaCoCo coverage reports

Surefire XML test results

Metrics tracked:

Total tests

Passing/failing tests

Line and branch coverage

Missed instructions

Uncovered classes

Edge-case coverage improvements

Bugs Found and Fixed by the Agent
NullPointerException Fix

The agent identified a NullPointerException in version-handling logic and inserted appropriate null checks.

Reflection Access Fix

The agent detected module-access failures and updated Maven with:

--add-opens


to resolve InaccessibleObjectException.

Broken Test Repairs

Multiple failing tests were automatically regenerated using improved logic.

Troubleshooting and FAQ
MCP server reports "cannot open file"

Ensure:

cwd = C:\Users\Administrator\se333-mcp\extensions

Pull request creation fails

Install GitHub CLI:

winget install GitHub.cli
gh auth login

Tools not discovered

Ensure mcp_server.py ends with:

if __name__ == "__main__":
    mcp.run()

Coverage report missing

Run:

mvn clean test jacoco:report

Conclusion

This project demonstrates a complete autonomous software engineering agent capable of:

Understanding the codebase

Generating and enhancing JUnit tests

Fixing defects

Performing static security analysis

Expanding test coverage

Handling Git automation

Creating pull requests

It satisfies all requirements for:

Phase 2: Core Tools

Phase 3: Git Automation

Phase 4: Intelligent Test Iteration

Phase 5: Creative Extensions
