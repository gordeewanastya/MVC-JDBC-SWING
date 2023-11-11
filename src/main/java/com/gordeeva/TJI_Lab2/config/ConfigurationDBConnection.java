package com.gordeeva.TJI_Lab2.config;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigurationDBConnection {
    @Value("${spring.datasource.url}")
    private static String url = "jdbc:postgresql://localhost:5432/hotel_complex";
    @Value("${spring.datasource.username}")
    private static String username = "postgres";
    @Value("${spring.datasource.password}")
    private static String password = "postgres";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connection established successfully");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

