package queryConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class showFood extends JFrame {
    private JTable foodTable;

    public showFood() {
        setTitle("Display Food from Database");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createGUI();
    }

    private void createGUI() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Price");
        model.addColumn("Category");
        model.addColumn("Description");
        model.addColumn("Item ID");

        foodTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(foodTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton displayButton = new JButton("Display Food");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFoodFromDatabase();
            }
        });
        panel.add(displayButton, BorderLayout.SOUTH);

        foodTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = foodTable.getSelectedRow();
                if (selectedRow != -1) {
                    printSelectedFoodDetails(selectedRow);
                }
            }
        });

        add(panel);
    }

    private void displayFoodFromDatabase() {
        DefaultTableModel model = (DefaultTableModel) foodTable.getModel();
        model.setRowCount(0);

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM foods";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String price = resultSet.getString("price");
                        String category = resultSet.getString("category");
                        String description = resultSet.getString("description");
                        String itemId = resultSet.getString("item_id");

                        model.addRow(new Object[]{price, category, description, itemId});
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database.");
        }
    }

    private void printSelectedFoodDetails(int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) foodTable.getModel();
        String price = (String) model.getValueAt(selectedRow, 0);
        String category = (String) model.getValueAt(selectedRow, 1);
        String description = (String) model.getValueAt(selectedRow, 2);
        String itemId = (String) model.getValueAt(selectedRow, 3);

        System.out.println("Selected Food Details:");
        System.out.println("Price: " + price);
        System.out.println("Category: " + category);
        System.out.println("Description: " + description);
        System.out.println("Item ID: " + itemId);
        System.out.println();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new showFood().setVisible(true);
            }
        });
    }
}
