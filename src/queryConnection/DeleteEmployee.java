package queryConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteEmployee {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new DeleteEmployeeFrame("Delete Employee");
            frame.setSize(300, 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class DeleteEmployeeFrame extends JFrame {
    private JTextField employeeIdField;
    private JLabel resultLabel;

    public DeleteEmployeeFrame(String title) {
        super(title);
        setLayout(new GridLayout(3, 2, 5, 5));

        add(new JLabel("Employee ID:"));
        employeeIdField = new JTextField();
        add(employeeIdField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchEmployee());
        add(searchButton);

        add(new JLabel("Result:"));
        resultLabel = new JLabel();
        add(resultLabel);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteEmployee());
        add(deleteButton);
    }

    private void searchEmployee() {
        String employeeId = employeeIdField.getText().trim();

        if (employeeId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee ID");
            return;
        }

        // JDBC database connection parameters
        String url = "jdbc:mysql://localhost:3306/your_database"; // Update with your database URL
        String username = "your_username";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM employee WHERE employee_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, employeeId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Employee found
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                        resultLabel.setText("Employee Found: " + firstName + " " + lastName);
                    } else {
                        // Employee not found
                        resultLabel.setText("Employee Not Found");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
        }
    }

    private void deleteEmployee() {
        String employeeId = employeeIdField.getText().trim();

        if (employeeId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee ID");
            return;
        }

        // JDBC database connection parameters
        String url = "jdbc:mysql://localhost:3306/your_database"; // Update with your database URL
        String username = "your_username";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "DELETE FROM employee WHERE employee_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, employeeId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Employee Deleted Successfully");
                    resultLabel.setText(""); // Clear the result label after deletion
                } else {
                    JOptionPane.showMessageDialog(this, "Employee Not Found");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
        }
    }
}
