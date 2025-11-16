import subprocess
import os

def run_maven_tests(project_path):
    proc = subprocess.run(
        ["mvn", "test", "-q"],
        cwd=project_path,
        capture_output=True,
        text=True
    )
    return proc.stdout + proc.stderr
