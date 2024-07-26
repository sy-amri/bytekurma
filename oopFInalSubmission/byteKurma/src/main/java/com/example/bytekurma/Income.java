//Ahmad Afif Danial bin Azhari, 22009264

package com.example.bytekurma;

import java.sql.*;

public class Income {
        private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bytekurma";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "Keithers05-";

        private int id;
        private int userId;
        private float amount;
        private String category;

        public Income(int userId, float amount, String type){
            this.userId = userId;
            this.amount = amount;
            this.category = type;
        }

        //Setters
        public void setId(int id) {
            this.id = id;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setAmount(float amount){
            this.amount = amount;
        }

        public void setCategory(String category){
            this.category = category;
        }

        //Getters
        public int getId() {
            return id;
        }

        public int getUserId(){
            return userId;
        }

        public float getAmount(){
            return amount;
        }

        public String getCategory(){
            return category;
        }

        //Methods
        public void insertIntoIncome(String category, float amount) {
            try {
                // Establish database connection
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

                // Retrieve current expense amount
                String queryCurrent = "SELECT " + category + " FROM income WHERE user_id = ?";
                PreparedStatement statementCurrent = connection.prepareStatement(queryCurrent);
                statementCurrent.setInt(1, userId);
                ResultSet resultSet = statementCurrent.executeQuery();

                float currentAmount = 0.0f;
                if (resultSet.next()) {
                    currentAmount = resultSet.getFloat(category);
                }

                // Calculate new amount by adding the current amount and the new amount
                float newAmount = currentAmount + amount;

                // Update the database with the new total amount
                String queryUpdate = "UPDATE income SET " + category + " = ? WHERE user_id = ?";
                PreparedStatement statementUpdate = connection.prepareStatement(queryUpdate);
                statementUpdate.setFloat(1, newAmount);
                statementUpdate.setInt(2, userId);
                statementUpdate.executeUpdate();

                statementCurrent.close();
                statementUpdate.close();
                connection.close();

                System.out.println(category + " expense updated successfully.");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void insertIntoTransactions(int userId, String date, String category, String type, float amount) {
            try {
                // Establish database connection
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

                // SQL query to insert data into history table with user_id
                String query = "INSERT INTO history (user_id, date, category, type, amount) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, userId);
                statement.setString(2, date);
                statement.setString(3, category);
                statement.setString(4, type);
                statement.setFloat(5, amount);

                // Execute the insert statement
                statement.executeUpdate();

                System.out.println("Data inserted into history table successfully.");

            } catch (SQLException e) {
                e.printStackTrace();  // Or handle the exception as needed
            }
        }

        public void updateBalance(int userId, float amount) {
            try {
                // Establish database connection
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

                // Retrieve current balance
                String queryCurrent = "SELECT balance FROM users WHERE user_id = ?";
                PreparedStatement statementCurrent = connection.prepareStatement(queryCurrent);
                statementCurrent.setInt(1, userId);
                ResultSet resultSet = statementCurrent.executeQuery();

                float currentBalance = 0.0f;
                if (resultSet.next()) {
                    currentBalance = resultSet.getFloat("balance");
                }

                // Calculate new balance by adding the current balance and the amount to add
                float newBalance = currentBalance + amount;

                // Update the database with the new balance
                String queryUpdate = "UPDATE users SET balance = ? WHERE user_id = ?";
                PreparedStatement statementUpdate = connection.prepareStatement(queryUpdate);
                statementUpdate.setFloat(1, newBalance);
                statementUpdate.setInt(2, userId);
                statementUpdate.executeUpdate();

                // Close resources
                resultSet.close();
                statementCurrent.close();
                statementUpdate.close();
                connection.close();

                System.out.println("Balance updated successfully.");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}
