package com.dartdirector.app;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class JDBCDriverVersion {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/dart_director";
        String user = "postgres";
        String password = "0iler567";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("Driver Name: " + metaData.getDriverName());
            System.out.println("Driver Version: " + metaData.getDriverVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
