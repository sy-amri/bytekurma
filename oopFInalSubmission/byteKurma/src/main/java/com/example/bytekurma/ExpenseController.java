//Syahir Amri bin Mohd Azha, 22007728

package com.example.bytekurma;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ExpenseController implements Initializable {
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
    private Button btnBack;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnSubmit;

    @FXML
    private ComboBox cboCategory;
    @FXML
    private ComboBox cboMethod;

    @FXML
    private TextField txtAmount;

    @FXML
    private DatePicker dtpDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cboCategory.getItems().addAll("Transportation", "Medical", "Food", "Miscellaneous");
        cboMethod.getItems().addAll("Cash", "Debit Card", "Credit Card");
        dtpDate.setValue(LocalDate.now());

        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int userId = DBUtils.getUserID(DBUtils.getUsername());
                    String type = "Expense";

                    LocalDate date = dtpDate.getValue();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String formattedDate = date.format(formatter);

                    if(cboCategory.getValue() == null || cboMethod.getValue() == null || txtAmount.getText().isEmpty()){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Please fill in all the fields!");
                        alert.showAndWait();

                    } else {
                        float amount = Float.parseFloat(txtAmount.getText());

                        if (cboCategory.getValue().equals("Transportation")) {
                            Expense expense = new Expense(userId, amount, "Transportation");
                            expense.insertIntoExpenses(expense.getCategory(), amount);
                            expense.insertIntoTransactions(expense.getUserId(), formattedDate, expense.getCategory(), type, amount);
                            expense.updateBalance(userId, amount);

                        } else if (cboCategory.getValue().equals("Medical")) {
                            Expense expense = new Expense(userId, amount, "Medical");
                            expense.insertIntoExpenses(expense.getCategory(), amount);
                            expense.insertIntoTransactions(expense.getUserId(), formattedDate, expense.getCategory(), type, amount);
                            expense.updateBalance(userId, amount);

                        } else if (cboCategory.getValue().equals("Food")) {
                            Expense expense = new Expense(userId, amount, "Food");
                            expense.insertIntoExpenses(expense.getCategory(), amount);
                            expense.insertIntoTransactions(expense.getUserId(), formattedDate, expense.getCategory(), type, amount);
                            expense.updateBalance(userId, amount);

                        } else if (cboCategory.getValue().equals("Miscellaneous")) {
                            Expense expense = new Expense(userId, amount, "Miscellaneous");
                            expense.insertIntoExpenses(expense.getCategory(), amount);
                            expense.insertIntoTransactions(expense.getUserId(), formattedDate, expense.getCategory(), type, amount);
                            expense.updateBalance(userId, amount);
                        }

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Your expense has been processed successfully!");
                        alert.showAndWait();

                        txtAmount.clear();
                        cboCategory.setValue(null);
                        cboMethod.setValue(null);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();  // Or handle the exception as needed
                }
            }
        });

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

        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtils.changeScene(event, "mainPage.fxml", "Byte Kurma");
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
