from fastmcp import FastMCP
import re
import os
import itertools

mcp = FastMCP("SpecTestGen")

@mcp.tool()
def boundary_tests(method_signature: str, output_path: str):
    params = re.findall(r"\((.*)\)", method_signature)
    if not params:
        return {"error": "No parameters detected"}
    param_list = params[0].split(",")
    boundaries = {}
    for p in param_list:
        p = p.strip()
        if any(t in p for t in ["int", "Integer", "float", "double", "long"]):
            name = p.split()[-1]
            boundaries[name] = [-1, 0, 1, 100, 101]
    test_code = "import org.junit.Test;\nimport static org.junit.Assert.*;\n\npublic class BoundaryTest {\n"
    for var, vals in boundaries.items():
        for v in vals:
            test_code += f"    @Test\n    public void test_{var}_{v}() {{ }}\n\n"
    test_code += "}"
    with open(output_path, "w") as f:
        f.write(test_code)
    return {"created": output_path}

@mcp.tool()
def equivalence_tests(method_signature: str, output_path: str):
    params = re.findall(r"\((.*)\)", method_signature)
    if not params:
        return {"error": "No parameters detected"}
    param_list = params[0].split(",")

    test_code = "import org.junit.Test;\nimport static org.junit.Assert.*;\n\npublic class EquivalenceTest {\n"
    for p in param_list:
        p = p.strip()
        name = p.split()[-1]
        if "String" in p:
            c = ["\"valid\"", "\"\"", "null"]
        elif any(t in p for t in ["int", "Integer"]):
            c = ["1", "0", "-1", "100"]
        else:
            c = ["null"]
        for v in c:
            label = v.replace("\"", "").replace("-", "neg")
            test_code += f"    @Test\n    public void test_{name}_{label}() {{ }}\n\n"
    test_code += "}"
    with open(output_path, "w") as f:
        f.write(test_code)
    return {"created": output_path}

@mcp.tool()
def decision_table_tests(java_file: str, output_path: str):
    if not os.path.exists(java_file):
        return {"error": "File not found"}
    with open(java_file, "r") as f:
        content = f.read()
    conditions = re.findall(r"if\s*\((.*?)\)", content)
    if not conditions:
        return {"error": "No conditions found"}
    cond = conditions[0]
    terms = [c.strip() for c in re.split(r"&&|\|\|", cond)]
    combos = list(itertools.product([True, False], repeat=len(terms)))
    test_code = "import org.junit.Test;\nimport static org.junit.Assert.*;\n\npublic class DecisionTableTest {\n"
    for i in range(len(combos)):
        test_code += f"    @Test\n    public void test_condition_{i}() {{ }}\n\n"
    test_code += "}"
    with open(output_path, "w") as f:
        f.write(test_code)
    return {"created": output_path}

if __name__ == "__main__":
    mcp.run()
