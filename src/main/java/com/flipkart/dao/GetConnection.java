package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/FlipFitSchema", "root",
                    "Radha84@cos");
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
