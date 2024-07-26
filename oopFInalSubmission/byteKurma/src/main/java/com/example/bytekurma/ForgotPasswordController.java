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

public class ForgotPasswordController implements Initializable {
    @FXML
    private Button btnBack;
    @FXML
    private Button btnContinue;

    @FXML
    private TextField txtUsername;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtils.changeScene(event, "hello-view.fxml", "Byte Kurma");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnContinue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = txtUsername.getText();

                try {
                    int userID = DBUtils.getUserID(username);  // Assuming DBUtils.getUserID retrieves user ID

                    if (txtUsername.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Field is empty! Please enter valid username.");
                        alert.showAndWait();
                    }
                    else if (userID != -1) {
                        DBUtils.setUsername(username);
                        DBUtils.changeScene(event, "changePassword.fxml", "Byte Kurma");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Username does not exists!");
                        alert.showAndWait();
                        // Handle the case where no expenses are found (optional)
                    }

                } catch (SQLException | IOException e) {
                    e.printStackTrace();  // Or handle the exception as needed
                }
            }
        });
    }
}
