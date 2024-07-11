package org.example;

import java.sql.*;

public class QueryUtils {

    public static void DummyQuery(Connection conn) throws SQLException {
        Statement stmt = null;
        try {
            // STEP 1: Create a statement
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT camera_id, name FROM mvms_camera LIMIT 5";

            // STEP 2: Execute query
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 3: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("camera_id");
                String name = rs.getString("name");

                //Display values
                System.out.print("ID: " + id);
                System.out.println(", name: " + name);
            }

            // STEP 4: Clean-up environment
            rs.close();
            stmt.close();
        } catch (Exception anyExc) {
            try {
                if(stmt != null) stmt.close();
            } catch (SQLException se2) {
                /* nothing we can do */
            }
            throw anyExc;
        }
    }
}
