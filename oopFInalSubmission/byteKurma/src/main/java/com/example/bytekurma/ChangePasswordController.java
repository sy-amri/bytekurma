//Daniela Adlin binti Razwan, 22008820

package com.example.bytekurma;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ChangePasswordController implements Initializable {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bytekurma";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Keithers05-";

    @FXML
    private Button btnBack;
    @FXML
    private Button btnContinue;

    @FXML
    private TextField txtNewPassword;
    @FXML
    private TextField txtConfirmPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtils.changeScene(event, "forgotPassword.fxml", "Byte Kurma");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnContinue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtNewPassword.getText().isEmpty() || txtConfirmPassword.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all the fields");
                    alert.showAndWait();
                } else if (txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
                    try {
                        Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                        int userID = DBUtils.getUserID(DBUtils.getUsername());
                        String newPassword = txtNewPassword.getText();

                        String query = "UPDATE users SET password = ? WHERE user_id = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, newPassword);
                        statement.setInt(2, userID);

                        int rowsUpdated = statement.executeUpdate();

                        if (rowsUpdated > 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Change Password");
                            alert.setHeaderText(null);
                            alert.setContentText("Your password has been changed");
                            alert.showAndWait();

                            DBUtils.changeScene(event, "hello-view.fxml", "Byte Kurma");
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Something went wrong! Please recheck.");
                            alert.showAndWait();
                        }

                        statement.close();
                        connection.close();

                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Passwords do not match! Please recheck.");
                    alert.showAndWait();
                }
            }
        });
    }
}
