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
        setSize(400, 80);
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
                //JOptionPane.showMessageDialog(foodMenu.this, "Showing items...");
                new showFood();
            }
        });

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new addFood();
            }
        });

        deleteItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteFood("Delete Food");
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
