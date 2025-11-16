import re

RESULT_REGEX = re.compile(
    r"Tests run: (\d+), Failures: (\d+), Errors: (\d+)"
)

def parse_maven_output(output):
    match = RESULT_REGEX.search(output)
    if not match:
        return {"error": "Could not parse maven output"}

    tests_run, failures, errors = match.groups()
    return {
        "tests_run": int(tests_run),
        "failures": int(failures),
        "errors": int(errors),
        "raw_output": output[-2000:]  # tail end of output
    }
