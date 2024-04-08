package com.techprime.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private static final String DB_URL = "jdbc:postgresql://localhost/ProjectManager";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "hari@2002";

    /*private static DataSource dataSource;

    static {
        // Initialize the DataSource
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(DB_URL);
        basicDataSource.setUsername(DB_USERNAME);
        basicDataSource.setPassword(DB_PASSWORD);
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");

        dataSource = basicDataSource;
    }*/

    public static Connection getConnection() throws SQLException {
        // Obtain a connection from the pool
    	Connection con = null;
    	try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return con;
    }
}
