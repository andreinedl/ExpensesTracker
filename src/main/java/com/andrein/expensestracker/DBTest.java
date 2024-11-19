package com.andrein.expensestracker;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {
    public void test() {
        String db = "jdbc:mariadb://localhost:3307/db?useSSL=false";
        String dbuser = "app";
        String dbpass = "password";

        try {
            System.out.println("Testing the connection to the Database...");
            Connection connection = DriverManager.getConnection(db, dbuser, dbpass);

            System.out.println("Connection successful.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
