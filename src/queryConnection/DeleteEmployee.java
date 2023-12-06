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


class DeleteEmployee extends JFrame {
    private JTextField employeeIdField;
    private JLabel resultLabel;
    
    public DeleteEmployee(String title) {
        super(title);
        setLayout(new GridLayout(3, 2, 5, 5));
        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        //main();
    }

    private void searchEmployee() {
        String employeeId = employeeIdField.getText().trim();

        if (employeeId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee ID");
            return;
        }

        // JDBC database connection parameters
        final String username = "eziegl4";
    	final String password = "COSC*26yaj";
    	final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/"+username+"db";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM Eziegl4db.Worker WHERE employee_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, employeeId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                    	// Employee found
                        String name = resultSet.getString("Name");
                        resultLabel.setText("Employee Found: " + name);
                    } else {
                       
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
        final String username = "eziegl4";
    	final String password = "COSC*26yaj";
    	final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/"+username+"db";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "DELETE FROM eziegl4db.Worker WHERE employee_id = ?";
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
