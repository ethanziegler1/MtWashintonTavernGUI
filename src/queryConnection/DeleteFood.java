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


class DeleteFood extends JFrame {
    private JTextField FoodIdField;
    private JLabel resultLabel;
    
    public DeleteFood(String title) {
        super(title);
        setLayout(new GridLayout(3, 2, 5, 5));
        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("Item ID:"));
        FoodIdField = new JTextField();
        add(FoodIdField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchFood());
        add(searchButton);

        add(new JLabel("Result:"));
        resultLabel = new JLabel();
        add(resultLabel);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteFood());
        add(deleteButton);
        this.setVisible(true);
    }

    private void searchFood() {
        String FoodId = FoodIdField.getText().trim();

        if (FoodId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee ID");
            return;
        }

        final String username = "eziegl4";
    	final String password = "COSC*26yaj";
    	final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/"+username+"db";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM eziegl4db.Food WHERE ItemID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, FoodId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String name = resultSet.getString("description");
                        resultLabel.setText("Food Found: " + name);
                    } else {
                       
                        resultLabel.setText("Food Not Found");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
        }
    }

    private void deleteFood() {
        String FoodId = FoodIdField.getText().trim();

        if (FoodId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Food ID");
            return;
        }

        final String username = "eziegl4";
    	final String password = "COSC*26yaj";
    	final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/"+username+"db";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "DELETE FROM eziegl4db.Food WHERE ItemID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, FoodId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Item Deleted Successfully");
                    resultLabel.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Item Not Found");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
        }
    }
}
