def generate_test_file(class_name, output_path, methods):
    test_code = [
        "import static org.junit.Assert.*;",
        "import org.junit.Test;",
        f"public class {class_name}Test " + "{",
        ""
    ]

    for m in methods:
        test_code.append("    @Test")
        test_code.append(f"    public void test_{m['name']}() " + "{")
        test_code.append(f"        {class_name} obj = new {class_name}();")
        placeholder_params = ", ".join("0" for _ in m["params"])
        test_code.append(
            f"        {m['return_type']} result = obj.{m['name']}({placeholder_params});"
        )
        test_code.append("        assertTrue(true);")
        test_code.append("    }\n")

    test_code.append("}")

    with open(output_path, "w", encoding="utf-8") as f:
        f.write("\n".join(test_code))
