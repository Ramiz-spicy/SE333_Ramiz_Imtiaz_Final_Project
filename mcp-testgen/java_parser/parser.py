import re

METHOD_REGEX = re.compile(
    r"(public|private|protected)\s+([\w<>]+)\s+(\w+)\s*\(([^)]*)\)"
)

def parse_java_file(path):
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()

    matches = METHOD_REGEX.findall(content)
    methods = []

    for mod, return_type, name, params in matches:
        param_list = [p.strip() for p in params.split(",")] if params.strip() else []
        methods.append({
            "name": name,
            "params": param_list,
            "return_type": return_type
        })

    return {"methods": methods}
