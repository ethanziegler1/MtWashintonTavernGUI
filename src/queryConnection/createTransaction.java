package queryConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class createTransaction extends JFrame {
    private JTextField customerIdField, nameField, tableNumField, serverIdField;
    private DefaultListModel<String> foodListModel, cartListModel;

    public createTransaction() {
        setTitle("Create Transaction");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        // First Window Components
        customerIdField = new JTextField();
        nameField = new JTextField();
        tableNumField = new JTextField();
        serverIdField = new JTextField();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButtonClicked();
            }
        });

        JPanel panel1 = new JPanel(new GridLayout(5, 2));
        panel1.add(new JLabel("Customer ID:"));
        panel1.add(customerIdField);
        panel1.add(new JLabel("Name:"));
        panel1.add(nameField);
        panel1.add(new JLabel("Table Number:"));
        panel1.add(tableNumField);
        panel1.add(new JLabel("Server ID:"));
        panel1.add(serverIdField);
        panel1.add(new JLabel(""));
        panel1.add(submitButton);

        // Second Window Components
        foodListModel = new DefaultListModel<>();
        JList<String> foodList = new JList<>(foodListModel);
        foodList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    addSelectedItemToCart(foodList.getSelectedValue());
                }
            }
        });

        JScrollPane foodScrollPane = new JScrollPane(foodList);

        // Third Window Components
        cartListModel = new DefaultListModel<>();
        JList<String> cartList = new JList<>(cartListModel);
        cartList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    removeItemFromCart(cartList.getSelectedValue());
                }
            }
        });

        JScrollPane cartScrollPane = new JScrollPane(cartList);

        // Adding components to the main frame
        add(panel1);
        add(foodScrollPane);
        add(cartScrollPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void submitButtonClicked() {
        String customerId = customerIdField.getText();
        String name = nameField.getText();
        String tableNum = tableNumField.getText();
        String serverId = serverIdField.getText();

        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Table Number: " + tableNum);
        System.out.println("Server ID: " + serverId);

        // TODO: Open the second window to display food items from the database
        // (You need to implement the database connection and retrieval of food items)
    }

    private void addSelectedItemToCart(String selectedItem) {
        cartListModel.addElement(selectedItem);
    }

    private void removeItemFromCart(String selectedItem) {
        cartListModel.removeElement(selectedItem);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new createTransaction());
    }
}
