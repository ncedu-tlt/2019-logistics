package ru.ncedu.logistics.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    private Connection connection;

    public Connection getConnection() {

        try {
            if(this.connection == null || this.connection.isClosed()){
                final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/logistic";
                final String USER = "w3";
                final String PASS = "dctghjcnj";
                this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return this.connection;
    }
}
