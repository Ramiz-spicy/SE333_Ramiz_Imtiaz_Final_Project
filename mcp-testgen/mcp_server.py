from fastmcp import FastMCP
import subprocess
import os
import json

mcp = FastMCP("Ultimate Agent Server")

# -------------------------
# Calculator Tool
# -------------------------

@mcp.tool(description="Add two numbers.")
def add(a: float, b: float) -> float:
    return a + b


# -------------------------
# Java Parsing
# -------------------------
@mcp.tool(description="Parse a Java file and return all method signatures.")
def parse_java(file_path: str):
    if not os.path.exists(file_path):
        return {"error": "File not found"}
    output = subprocess.getoutput(f'grep -E "(public|private|protected).*\\(" "{file_path}"')
    return {"methods": output}


# -------------------------
# Test Generation
# -------------------------
@mcp.tool(description="Generate a JUnit test file for the given Java class.")
def generate_tests(class_name: str, output_path: str, methods: list[str]):
    code = f"import org.junit.Test;\nimport static org.junit.Assert.*;\n\npublic class {class_name}Test {{\n"
    for m in methods:
        code += (
            f"    @Test\n"
            f"    public void test_{m}() {{\n"
            f"        // TODO: implement test\n"
            f"    }}\n\n"
        )
    code += "}"
    with open(output_path, "w") as f:
        f.write(code)
    return {"created": output_path}


# -------------------------
# Maven test runner
# -------------------------
@mcp.tool(description="Run mvn test inside the given project directory.")
def run_tests(project_path: str):
    cmd = f"cd {project_path} && mvn -q test"
    result = subprocess.getoutput(cmd)
    return {"result": result}


# -------------------------
# Git Tools
# -------------------------
@mcp.tool(description="Return clean status, staged files, and conflicts from Git.")
def git_status():
    out = subprocess.getoutput("git status --porcelain=v1")
    return {"status": out}


@mcp.tool(description="Stage all changed files (git add -A).")
def git_add_all():
    subprocess.getoutput("git add -A")
    out = subprocess.getoutput("git status --porcelain=v1")
    return {"staged": out}


@mcp.tool(description="Commit staged files with a provided commit message.")
def git_commit(message: str):
    cmd = f'git commit -m "{message}"'
    out = subprocess.getoutput(cmd)
    return {"commit": out}


@mcp.tool(description="Push commits to the given Git remote (default origin).")
def git_push(remote: str = "origin"):
    out = subprocess.getoutput(f"git push {remote} HEAD")
    return {"push": out}


@mcp.tool(description="Create a GitHub pull request using the GitHub CLI.")
def git_pull_request(base: str = "main", title: str = "Auto PR", body: str = "Automated update"):
    gh = f'gh pr create --base "{base}" --title "{title}" --body "{body}"'
    out = subprocess.getoutput(gh)
    return {"pr_url": out}


# -------------------------
# Boundary Testing (Extension #1)
# -------------------------
@mcp.tool(description="Generate boundary value test cases for a Java method.")
def generate_boundary_tests(method_signature: str):
    test_cases = {
        "min": -2147483648,
        "zero": 0,
        "max": 2147483647
    }
    return {"boundary_cases": test_cases}


# -------------------------
# Security Scan (Extension #2)
# -------------------------
@mcp.tool(description="Scan a Java file for common security vulnerabilities.")
def security_scan_java(file_path: str):
    findings = []

    if not os.path.exists(file_path):
        return {"error": "File not found"}

    code = open(file_path).read()

    if "Statement.executeQuery" in code:
        findings.append("Possible SQL Injection (use PreparedStatement instead).")

    if "secret" in code.lower() or "password" in code.lower():
        findings.append("Hard-coded credentials detected.")

    if "getParameter" in code and "+ " in code:
        findings.append("User input concatenated into output (possible XSS).")

    return {"findings": findings}

# test change

# -------------------------
# Run MCP server (ONLY ONE!)
# -------------------------
if __name__ == "__main__":
    mcp.run()
