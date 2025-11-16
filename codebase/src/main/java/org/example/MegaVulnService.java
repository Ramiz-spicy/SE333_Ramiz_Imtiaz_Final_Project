package org.example;

import java.io.*;
import java.security.MessageDigest;
import java.sql.*;
import java.util.Random;

public class MegaVulnService {

    private String hardcodedPassword = "secret123";

    public void handleRequest(String userInput, String fileName, String cmd) throws Exception {

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/test", "root", "password"
        );
        Statement stmt = conn.createStatement();
        stmt.executeQuery("SELECT * FROM users WHERE name = '" + userInput + "'");

        Runtime.getRuntime().exec(cmd);

        File f = new File("/var/data/" + fileName);
        FileInputStream fis = new FileInputStream(f);

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userInput.getBytes());

        Random r = new Random();
        int x = r.nextInt();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.bin"));
        Object obj = ois.readObject();

        ClassLoader cl = ClassLoader.getSystemClassLoader();
        cl.loadClass(userInput);

        System.out.println("Hello " + userInput);
    }
}
