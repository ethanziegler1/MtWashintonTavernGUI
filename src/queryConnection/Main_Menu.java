package queryConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Menu extends JFrame {
    public Main_Menu() {
        // Set the title of the JFrame
        setTitle("My GUI");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons
        JButton foodButton = new JButton("Food");
        JButton employeeButton = new JButton("Employee");
        JButton customerButton = new JButton("Customer");

        // Create a panel to hold the buttons
        JPanel panel = new JPanel();

        // Add buttons to the panel
        panel.add(foodButton);
        panel.add(employeeButton);
        panel.add(customerButton);

        // Set the layout manager for the panel (optional)
        // panel.setLayout(new FlowLayout());

        // Add the panel to the content pane of the frame
        add(panel);

        // Add action listeners to the buttons
        foodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Food button click
                System.out.println("Food button clicked");
            }
        });

        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Employee button click
                System.out.println("Employee button clicked");
            }
        });

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Customer button click
                System.out.println("Customer button clicked");
            }
        });

        // Set the size of the frame
        setSize(300, 200);

        // Set the frame to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of MyGUI
        Main_Menu myGUI = new Main_Menu();
    }
}