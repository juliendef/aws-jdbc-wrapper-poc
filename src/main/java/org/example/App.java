package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import software.amazon.jdbc.PropertyDefinition;

/** Advanced boilerplate code for JDBC connection to a MySQL database using the wrapper */
public class App {
    private static final Config CONNECTION_CONFIG = Config.getInstance();

    public static void main(String[] args) throws SQLException {

        final Properties props = new Properties();
        // Enable readWriteSplitting, failover, and efm2 plugins and set properties
        props.setProperty(PropertyDefinition.PLUGINS.name, "readWriteSplitting,failover,efm2");
        props.setProperty(PropertyDefinition.USER.name, CONNECTION_CONFIG.getUsername());
        props.setProperty(PropertyDefinition.PASSWORD.name, CONNECTION_CONFIG.getPassword());
        // !! GET DETAILS ABOUT TOPOLOGY
        props.setProperty("wrapperLoggerLevel", "finest");

        // this way it should automatically close the connection (maybe not, assumption from my old memory)
        try (final Connection connection = DriverManager.getConnection(CONNECTION_CONFIG.getUrl(), props)) {
            System.out.println("Connection established");
            QueryUtils.DummyQuery(connection);
        }
    }
}
