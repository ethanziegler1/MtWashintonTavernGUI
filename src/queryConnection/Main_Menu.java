package queryConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Menu extends JFrame {
    public static currUser user = new currUser("");
    public Main_Menu() {
        setTitle("My GUI");

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JButton foodButton = new JButton("Food");
        JButton employeeButton = new JButton("Employee");
        JButton customerButton = new JButton("Customer");

        JPanel panel = new JPanel();

   
        panel.add(foodButton);
        panel.add(employeeButton);
        panel.add(customerButton);

      
        add(panel);

        foodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new foodMenu();
                System.out.println("Food button clicked");
            }
        });

        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeManagementApp("Employee Management App");
                System.out.println("Employee button clicked");
            }
        });

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new Customer_Menu();
                System.out.println("Customer button clicked");
            }
        });

        setSize(300, 200);

        setVisible(true);
    }

    public static void main(String[] args) {
        Main_Menu myGUI = new Main_Menu();
    }
}