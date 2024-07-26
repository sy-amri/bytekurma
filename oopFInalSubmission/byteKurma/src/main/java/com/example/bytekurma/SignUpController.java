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
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSignUp;

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtConfirmPassword;

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

        btnSignUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = txtUsername.getText();
                String password = txtPassword.getText();
                String confirmPassword = txtConfirmPassword.getText();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all the fields!");
                    alert.showAndWait();

                } else if (Objects.equals(password, confirmPassword)) {
                    try {
                        DBUtils.signUp(event, username, password, confirmPassword);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Passwords do not match!");
                    alert.showAndWait();
                }
            }
        });
    }
}
