package utilities;

import java.sql.*;

public class JdbcConnection {

    private static Connection connection;

    private JdbcConnection() {

    }

    public static void init() {
        if (connection == null) {
            try {
                connection = DriverManager
                        .getConnection("jdbc:mysql://localhost/w3schools?user=root&password=Rsjep@1993");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ResultSet getData(String query) {
        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
