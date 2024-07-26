//Muhammad Aqil bin Anuar, 24000198

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

public class AccountController implements Initializable {
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
    private Button btnEdit;

    @FXML
    private Label lblUsername;
    @FXML
    private Label lblBalance;

    @FXML
    private DatePicker dtpDOB;

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtEmail;

    private boolean isEditing = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (lblUsername != null) {
            lblUsername.setText("[" + DBUtils.getUsername() + "]");
        }

        txtFirstName.setEditable(false);
        txtLastName.setEditable(false);
        txtUsername.setEditable(false);
        txtEmail.setEditable(false);
        dtpDOB.setDisable(true);
        btnEdit.setText("Edit");
        updateUI();

        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isEditing) {
                    // Enable editing
                    txtFirstName.setEditable(true);
                    txtLastName.setEditable(true);
                    txtUsername.setEditable(true);
                    txtEmail.setEditable(true);
                    dtpDOB.setDisable(false);
                    btnEdit.setText("Done");

                } else {
                    // Save changes to database and disable editing
                    try  {
                        Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                        int userID = DBUtils.getUserID(DBUtils.getUsername());  // Assuming DBUtils.getUserID retrieves user ID

                        String updateQuery = "UPDATE users SET firstName = ?, lastName = ?, username = ?, email = ?, DOB = ? WHERE user_id = ?";
                        PreparedStatement statement = connection.prepareStatement(updateQuery);
                        statement.setString(1, txtFirstName.getText());
                        statement.setString(2, txtLastName.getText());
                        statement.setString(3, txtUsername.getText());
                        statement.setString(4, txtEmail.getText());

                        LocalDate dob = dtpDOB.getValue();
                        if (dob != null) {
                            statement.setDate(5, java.sql.Date.valueOf(dob)); // Convert LocalDate to java.sql.Date
                        } else {
                            statement.setNull(5, java.sql.Types.DATE); // Set null in database if LocalDate is null
                        }

                        statement.setInt(6, userID);
                        statement.executeUpdate();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    txtFirstName.setEditable(false);
                    txtLastName.setEditable(false);
                    txtUsername.setEditable(false);
                    txtEmail.setEditable(false);
                    dtpDOB.setDisable(true);
                    btnEdit.setText("Edit");

                    updateUI();
                }
                isEditing = !isEditing;
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

    private void updateUI(){
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            int userID = DBUtils.getUserID(DBUtils.getUsername());  // Assuming DBUtils.getUserID retrieves user ID

            String query = "SELECT balance, firstName, lastName, username, email, DOB FROM users WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                float balance = resultSet.getFloat("balance");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");

                // Check if dob is null before converting
                LocalDate dob = null;
                Date sqlDate = resultSet.getDate("DOB");
                if (sqlDate != null) {
                    dob = sqlDate.toLocalDate();
                }

                User user = new User(userID, balance, firstName, lastName, username, email, dob);

                lblBalance.setText(String.valueOf(user.getBalance()));
                txtFirstName.setText(user.getFirstName());
                txtLastName.setText(user.getLastName());
                txtUsername.setText(user.getUsername());
                txtEmail.setText(user.getEmail());
                dtpDOB.setValue(user.getDOB());

                if (dob != null) {
                    dtpDOB.setValue(dob);
                } else {
                    dtpDOB.setValue(null); // Clear DatePicker if dob is null
                }
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();  // Or handle the exception as needed
        }
    }
}
