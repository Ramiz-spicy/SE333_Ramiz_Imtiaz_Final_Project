from fastmcp import FastMCP
import re
import os

mcp = FastMCP("Advanced Security Analyzer")

dangerous = {
    "sql_injection": r"(executeQuery|prepareStatement)\(.+\+",
    "deserialization": r"ObjectInputStream",
    "command_exec": r"Runtime\.getRuntime\(\)\.exec",
    "weak_hash_md5": r"MessageDigest\.getInstance\(\"MD5\"\)",
    "weak_hash_sha1": r"MessageDigest\.getInstance\(\"SHA-1\"\)",
    "insecure_random": r"new\s+Random\(",
    "path_traversal": r"new\s+File\(.+userInput",
    "reflected_input": r"response\.getWriter\(\).+request\.getParameter",
    "classloader_rce": r"ClassLoader\.getSystemClassLoader",
    "hardcoded_secret": r"(password|secret|token)\s*=\s*[\"']",
    "logging_sensitive": r"log\..*(password|secret|token)"
}

def taint_analysis(text):
    params = re.findall(r"request\.getParameter\(\"(.+?)\"\)", text)
    tainted = [p for p in params]
    findings = []
    for t in tainted:
        if re.search(fr"{t}.*executeQuery", text):
            findings.append(f"tainted_sql:{t}")
        if re.search(fr"{t}.*File", text):
            findings.append(f"tainted_path:{t}")
        if re.search(fr"{t}.*exec", text):
            findings.append(f"tainted_exec:{t}")
    return findings

@mcp.tool()
def analyze_java(file_path: str):
    if not os.path.exists(file_path):
        return {"error": "file not found"}

    text = open(file_path, "r", encoding="utf-8").read()
    results = []

    for name, pat in dangerous.items():
        if re.search(pat, text, flags=re.IGNORECASE):
            results.append(name)

    taints = taint_analysis(text)
    results.extend(taints)

    autofix = []
    if "weak_hash_md5" in results:
        autofix.append("Replace MD5 with SHA-256")
    if "insecure_random" in results:
        autofix.append("Replace java.util.Random with SecureRandom")
    if any("sql" in r for r in results):
        autofix.append("Use PreparedStatement with placeholders")

    return {
        "security_issues": results,
        "autofix_suggestions": autofix
    }

if __name__ == "__main__":
    mcp.run()
