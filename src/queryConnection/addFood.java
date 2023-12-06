package queryConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addFood extends JFrame {
    private JTextField priceTextField, descriptionTextField, itemIdTextField;
    private JComboBox<String> categoryComboBox;

    public addFood() {
        setTitle("Add Food to Database");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("Price:"));
        priceTextField = new JTextField();
        panel.add(priceTextField);

        panel.add(new JLabel("Category:"));
        String[] categories = {"Brunch", "Lunch/Dinner", "Appetizer", "Drink"};
        categoryComboBox = new JComboBox<>(categories);
        panel.add(categoryComboBox);

        panel.add(new JLabel("Description:"));
        descriptionTextField = new JTextField();
        panel.add(descriptionTextField);

        panel.add(new JLabel("Item ID:"));
        itemIdTextField = new JTextField();
        panel.add(itemIdTextField);

        JButton addButton = new JButton("Add Food");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFoodToDatabase();
            }
        });
        panel.add(addButton);

        add(panel);
       
    }



    private void addFoodToDatabase() {
        String price = priceTextField.getText();
        String category = (String) categoryComboBox.getSelectedItem();
        String description = descriptionTextField.getText();
        String itemId = itemIdTextField.getText();

        final String username = "eziegl4";
    	final String password = "COSC*26yaj";
    	final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/"+username+"db";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO Food (Price, Categor, Description, itemID) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, price);
                preparedStatement.setString(2, category);
                preparedStatement.setString(3, description);
                preparedStatement.setString(4, itemId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Food added to database successfully.");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add food to database.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database.");
        }
    }

    private void clearFields() {
        priceTextField.setText("");
        categoryComboBox.setSelectedIndex(0);
        descriptionTextField.setText("");
        itemIdTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new addFood().setVisible(true);
            }
        });
    }
}
