//Muhammad Aqil bin Anuar, 24000198
//Daniela Adlin binti Razwan, 22008820

package com.example.bytekurma;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bytekurma";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Keithers05-";

    @FXML
    private Button btnHome;
    @FXML
    private Button btnHistory;
    @FXML
    private Button btnAccount;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnReoccurringExpense;
    @FXML
    private Button btnExpense;
    @FXML
    private Button btnIncome;

    @FXML
    private Label lblUsername;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblTransportation;
    @FXML
    private Label lblMedical;
    @FXML
    private Label lblFood;
    @FXML
    private Label lblMiscellaneous;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (lblUsername != null) {
            lblUsername.setText("[" + DBUtils.getUsername() + "]");
        }

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            int userID = DBUtils.getUserID(DBUtils.getUsername());  // Assuming DBUtils.getUserID retrieves user ID

            String query = "SELECT transportation, food, medical, miscellaneous FROM expenses WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);  // Set user ID as the parameter

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                float total, transport, food, medical, miscellaneous;

                // Extract expense values
                transport = resultSet.getFloat("transportation");
                food = resultSet.getFloat("food");
                medical = resultSet.getFloat("medical");
                miscellaneous = resultSet.getFloat("miscellaneous");

                total = transport + food + medical + miscellaneous;

                if (lblTransportation != null){
                    lblTransportation.setText("RM " + transport);
                }
                if (lblMedical != null){
                    lblMedical.setText("RM " + medical);
                }
                if (lblFood != null){
                    lblFood.setText("RM " + food);
                }
                if (lblMiscellaneous != null){
                    lblMiscellaneous.setText("RM " + miscellaneous);
                }
                if (lblTotal != null){
                    lblTotal.setText("RM " + total);
                }

            } else {
                System.out.println("No expense data found for user ID: " + userID);
                // Handle the case where no expenses are found (optional)
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();  // Or handle the exception as needed
        }

        btnHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtils.changeScene(event, "history.fxml", "Byte Kurma");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtils.changeScene(event, "account.fxml", "Byte Kurma");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnLogOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Logout");
                alert.setHeaderText("Logout");
                alert.setContentText("Are you sure you want to logout?");

                if (alert.showAndWait().get() == ButtonType.OK){
                    try {
                        DBUtils.changeScene(event, "hello-view.fxml", "Byte Kurma");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
