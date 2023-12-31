import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String JDBC_URL = "jdbc:sql://localhost:3306/cab";
    private static final String JDBC_USER = "your_username";
    private static final String JDBC_PASSWORD = "your_password";
    private static final String JDBC_STATUS = "status" ;
    private static final String JDBC_ID = "ID" ;



    public static void registerUser(User user) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean loginUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updateCabAvailability(int cabId, boolean availability) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_ID, JDBC_STATUS);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Cabs status = ? WHERE id = ?")) {
            preparedStatement.setBoolean(1, availability);
            preparedStatement.setInt(2, cabId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ResultSet queryAllCabs() {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_ID, JDBC_STATUS);
            return connection.createStatement().executeQuery("SELECT * FROM cab");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}

