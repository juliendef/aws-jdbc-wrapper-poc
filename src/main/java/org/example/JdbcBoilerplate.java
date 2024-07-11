package org.example;

import java.sql.*;

/** Basic boilerplate code for JDBC connection to a MySQL database. */
public class JdbcBoilerplate {

    // depends on the version of mysql-connector-java (here we're using mysql-connector-j)
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final Config CONNECTION_CONFIG = Config.getInstance();

    public static void main(String[] args) {
        Connection conn = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(
                CONNECTION_CONFIG.getUrl(),
                CONNECTION_CONFIG.getUsername(),
                CONNECTION_CONFIG.getPassword()
            );

            // STEP 3: Execute a query
            QueryUtils.DummyQuery(conn);

            conn.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                if (conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
