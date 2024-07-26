//Syahir Amri bin Mohd Azha, 22007728
//Ahmad Afif Danial bin Azhari, 22009264
//Manas Ismail Abdylas, 22008600

package com.example.bytekurma;

import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DBUtils {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bytekurma";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Keithers05-";

    private static String username;

    public static void setUsername(String username) {
        DBUtils.username = username;
    }

    public static String getUsername() {
        return DBUtils.username;
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String title) throws IOException {
        Parent root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    private static boolean usernameExists(Connection connection, String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        resultSet.close();
        statement.close();

        return count > 0;
    }

    public static int getUserID(String username) throws SQLException {
        int userID = -1; // Initialize to -1 indicating not found

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT user_id FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username); // Set username parameter

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userID = resultSet.getInt("user_id"); // Retrieve user ID from result
            } else {
                System.out.println("User not found: " + username); // Handle not found case
            }
            resultSet.close(); // Close result set
        } catch (SQLException e) {
            throw e; // Re-throw the SQLException for caller to handle
        }

        return userID;
    }

    public static void signUp(ActionEvent event, String username, String password, String confirmPassword) throws IOException {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            connection.setAutoCommit(false); // Start a transaction (optional)

            String query = "INSERT INTO users (username, password, balance) VALUES (?, ?, 0)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            // Check if username exists
            if (usernameExists(connection, username)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Username already exists!");
                alert.showAndWait();
                return;
            }

            statement.executeUpdate();
            connection.commit();

            // Now insert into expenses table
            connection.setAutoCommit(false); // Start another transaction (optional)
            query = "INSERT INTO expenses (user_id, transportation, food, medical, miscellaneous) VALUES (?, 0, 0, 0, 0)";
            statement = connection.prepareStatement(query);

            // Retrieve the user_id after it's inserted into users
            int userId = getUserID(username);
            statement.setInt(1, userId);
            statement.executeUpdate();

            connection.commit();

            changeScene(event, "mainPage.fxml", "Byte Kurma");
        } catch (SQLException e) {
            // Handle SQL exceptions (e.g., rollback transaction)
            e.printStackTrace(); // Or log the error for debugging
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void logIn(ActionEvent event, String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields!");
            alert.showAndWait();
            return;
        }

        try {
            // Establishing connection to the MySQL server
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT password FROM users WHERE username = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                System.out.println("User does not exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Incorrect credentials!");
                alert.showAndWait();
            } else {
                // User exists, check password
                String retrievedPassword = result.getString("password");
                if (retrievedPassword.equals(password)) {
                    // Successful login, change scene or perform action
                    changeScene(event, "mainPage.fxml", "Byte Kurma");
                } else {
                    System.out.println("Password did not match!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Incorrect credentials!");
                    alert.showAndWait();
                }
            }

            // Close resources
            statement.close();
            connection.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}


