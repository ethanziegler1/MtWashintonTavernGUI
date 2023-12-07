package queryConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class foodMenu extends JFrame {
    // Constructor
    public foodMenu() {
        // Set frame properties
        setTitle("Food Menu");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Center the frame on the screen

        // Create buttons
        JButton showItemsButton = new JButton("Show Items");
        JButton addItemButton = new JButton("Add Item");
        JButton deleteItemButton = new JButton("Delete Item");

        // Set layout manager
        setLayout(new FlowLayout());

        // Add buttons to the frame
        add(showItemsButton);
        add(addItemButton);
        add(deleteItemButton);

        // Add action listeners for each button
        showItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add functionality to show items
                JOptionPane.showMessageDialog(foodMenu.this, "Showing items...");
            }
        });

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add functionality to add item
                String newItem = JOptionPane.showInputDialog(foodMenu.this, "Enter new item:");
                // You can handle the addition of the item here
                if (newItem != null && !newItem.isEmpty()) {
                    // Add the item to the menu
                    JOptionPane.showMessageDialog(foodMenu.this, "Item added: " + newItem);
                }
            }
        });

        deleteItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add functionality to delete item
                String itemToDelete = JOptionPane.showInputDialog(foodMenu.this, "Enter item to delete:");
                // You can handle the deletion of the item here
                if (itemToDelete != null && !itemToDelete.isEmpty()) {
                    // Delete the item from the menu
                    JOptionPane.showMessageDialog(foodMenu.this, "Item deleted: " + itemToDelete);
                }
            }
        });

        setVisible(true);
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new foodMenu().setVisible(true);
            }
        });
    }
}
