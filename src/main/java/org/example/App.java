package org.example;

import software.amazon.jdbc.PropertyDefinition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** Advanced boilerplate code for JDBC connection to a MySQL database using the wrapper */
public class App {
    private static final Config CONNECTION_CONFIG = Config.getInstance();

    public static void main(String[] args) throws SQLException {
        final Properties props = getProperties();

        // this way it should automatically close the connection (maybe not, assumption from my old memory)
        try (final Connection connection = DriverManager.getConnection(CONNECTION_CONFIG.getUrl(), props)) {
            System.out.println("Connection established");
            connection.setReadOnly(true);
            int i = 0;
            while (i < 1) {
                QueryUtils.DummyQuery(connection);
                i++;
            }
        }
    }

    private static Properties getProperties() {
        final Properties props = new Properties();
        // Enable readWriteSplitting, failover, and efm2 plugins and set properties
        props.setProperty(PropertyDefinition.PLUGINS.name, "readWriteSplitting,failover,efm2");
        props.setProperty(PropertyDefinition.USER.name, CONNECTION_CONFIG.getUsername());
        props.setProperty(PropertyDefinition.PASSWORD.name, CONNECTION_CONFIG.getPassword());
        // !! GET DETAILS ABOUT TOPOLOGY
        props.setProperty(PropertyDefinition.LOGGER_LEVEL.name, "ALL");
        return props;
    }
}
