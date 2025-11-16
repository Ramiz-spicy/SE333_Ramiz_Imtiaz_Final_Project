This project implements an AI-driven development assistant using the Model Context Protocol (MCP).
The agent automates testing, improves coverage iteratively, performs security scanning, generates boundary test cases, and integrates with Git for automated commits and pull requests.

This README serves as the complete technical manual required for the SE333 final project.


2. Installation and Configuration Guide

Follow these steps to run the MCP server inside VS Code.

Step 1 — Clone the repository
git clone https://github.com/Ramiz-spicy/SE333_Ramiz_Imtiaz_Final_Project.git
cd SE333_Ramiz_Imtiaz_Final_Project

Step 2 — Create and activate a Python virtual environment

Windows:

python -m venv venv
.\venv\Scripts\activate

Step 3 — Install required packages
pip install -r requirements.txt

Step 4 — Configure MCP in VS Code

Open this file:

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
The server will appear in the "MCP Servers" list as "Ultimate Agent Server".

Step 5 — Verify tool discovery

Run:

/help


You should see all tools, including:

parse_java
generate_tests
run_tests
git_status
git_add_all
git_commit
git_push
git_pull_request
generate_boundary_tests
security_scan_java

3. MCP Tool / API Documentation

Below is documentation for every implemented tool.

parse_java(file_path)

Extracts all method signatures from a Java file.

Input:

{ "file_path": "path/to/MyClass.java" }


Output:

{ "methods": ["add", "divide", "isEmpty", ...] }

generate_tests(class_name, output_path, methods)

Generates a JUnit test skeleton based on parsed methods.

Input:

{
  "class_name": "MyClass",
  "output_path": "codebase/src/test/java/org/example/MyClassTest.java",
  "methods": ["add", "divide"]
}


Output:

{"created": "path/to/MyClassTest.java"}

run_tests(project_path)

Runs Maven test pipeline with JaCoCo coverage.

Input:

{ "project_path": "codebase" }


Output:
Full Maven output, test results, and coverage.

git_status()

Shows modified or staged files.

git_add_all()

Stages all modified files.

git_commit(message)

Commits staged files using the provided commit message.

Input:

{ "message": "Updated test suite" }

git_push(remote)

Pushes commits to remote. Defaults to "origin".

git_pull_request(base, title, body)

Creates a pull request using GitHub CLI.
If GitHub CLI is unavailable, the system creates a branch and provides a URL for manual PR creation.

generate_boundary_tests(method_signature)

Generates boundary test cases using min, zero, and max int values.

Output:

{
  "boundary_cases": {
    "min": -2147483648,
    "zero": 0,
    "max": 2147483647
  }
}

security_scan_java(file_path)

Performs static analysis to detect common vulnerabilities, including:

SQL injection
Command injection
Hard-coded credentials
Unsafe string concatenation

Example output:

{
  "findings": [
    "Possible SQL Injection",
    "Hard-coded credentials detected"
  ]
}

4. Phase 4: Intelligent Test Iteration

The intelligent test generator improves tests over multiple iterations.

Step A — Parse the Java class
/parse_java codebase/src/main/java/org/example/MyClass.java

Step B — Generate or enhance tests
/generate_tests MyClass codebase/src/test/java/org/example/MyClassTest.java ["add","divide","factorial"]

Step C — Run tests and generate JaCoCo report
/run_tests codebase


Coverage reports appear under:

codebase/target/site/jacoco/index.html

Step D — Improve tests iteratively

Invoke prompt-based behavior:

/improve codebase/src/main/java/org/example/MyClass.java


This triggers:

Adding new test cases

Increasing branch coverage

Fixing failing or brittle tests

Re-running JaCoCo

Step E — Fixing discovered bugs

If a test exposes a bug:

The agent proposes a patch

You approve

It commits and pushes

A PR is created or prepared

5. Quality Metrics Dashboard

Generated via JaCoCo after running:

mvn clean test jacoco:report


Metrics include:

Line coverage

Branch coverage

Missed instructions

Missed branches

Complexity metrics

Reports located at:

codebase/target/site/jacoco/index.html

6. Troubleshooting and FAQ

Issue: "Server exited before responding to initialize"
Cause: Incorrect cwd path.
Fix: Ensure mcp.json cwd matches:

C:\Users\Administrator\se333-mcp\extensions


Issue: Tools not discovered
Fix: Ensure mcp_server.py ends with one instance of:

if __name__ == "__main__":
    mcp.run()


Issue: PR cannot be created
Fix: Install GitHub CLI:

winget install --id GitHub.cli


Issue: JaCoCo not generating
Fix:

cd codebase
mvn clean test jacoco:report

7. Author

Ramiz Imtiaz
SE333 – Software Testing and Analysis
DePaul University
