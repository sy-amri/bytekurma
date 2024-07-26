//Muhammad Aqil bin Anuar, 24000198

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
import java.util.ResourceBundle;

public class BKController implements Initializable {
    @FXML
    private Button btnPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSignUp;

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final String username = txtEmail.getText();
                final String password = txtPassword.getText();

                if (username.isEmpty() || password.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all the fields!");
                    alert.showAndWait();

                } else {
                    DBUtils.setUsername(username);
                    DBUtils.logIn(event, username, password);
                }
            }
        });

        btnPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtils.changeScene(event, "forgotPassword.fxml", "Byte Kurma");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnSignUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtils.changeScene(event, "signUp.fxml", "Byte Kurma");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}