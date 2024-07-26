//Syahir Amri bin Mohd Azha, 22007728

package com.example.bytekurma;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bytekurma";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Keithers05-";

    @FXML
    private Button btnHome;
    @FXML
    private Button btnAccount;
    @FXML
    private Button btnLogOut;

    @FXML
    private TableView<History> tblHistory;
    @FXML
    private TableColumn<History, String> colDate;
    @FXML
    private TableColumn<History, String> colCategory;
    @FXML
    private TableColumn<History, String> colType;
    @FXML
    private TableColumn<History, Float> colAmount;

    @FXML
    private Label lblUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (lblUsername != null) {
            lblUsername.setText("[" + DBUtils.getUsername() + "]");
        }

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            int userID = DBUtils.getUserID(DBUtils.getUsername());  // Assuming DBUtils.getUserID retrieves user ID
            ObservableList<History> tableData = FXCollections.observableArrayList();

            String query = "SELECT date, category, type, amount FROM history WHERE user_id = ? LIMIT 10";;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String date = resultSet.getString("date");
                String category = resultSet.getString("category");
                String type = resultSet.getString("type");
                float amount = resultSet.getFloat("amount");

                tableData.add(new History(date, category, type, amount));
            }

            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
            colType.setCellValueFactory(new PropertyValueFactory<>("type"));
            colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

            tblHistory.setItems(tableData);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtils.changeScene(event, "mainPage.fxml", "Byte Kurma");
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
