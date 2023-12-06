package queryConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import queryConnection.*;


public class EmployeeManagementApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmployeeManagementFrame("Employee Management");
            
        });
    }


    private static JFrame frame = new JFrame();
    private static JLabel resultLabel;

    public static void EmployeeManagementFrame(String title) {
        
        frame = new JFrame(title);
        frame.setLayout(new GridLayout(2, 2, 5, 5));

        resultLabel = new JLabel();

        JButton searchButton = new JButton("Search Employee");
        searchButton.addActionListener(e -> searchEmployee());
        frame.add(searchButton);

        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.addActionListener(e -> deleteEmployee());
        frame.add(deleteButton);

        JButton createEmp = new JButton("Create Employee");
        createEmp.addActionListener(e -> createEmployee());
        frame.add(createEmp);

        JButton viewStaffButton = new JButton("View Staff");
        viewStaffButton.addActionListener(e -> viewStaff());
        frame.add(viewStaffButton);

        frame.add(resultLabel);
        frame.setSize(400, 150);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setVisible(true);
    }

    private static void searchEmployee() {
        // Add your search employee logic here
        System.out.println("Searching Employee...");
        JFrame temp = new EmployeeSearchFrame("Employee Search");
        temp.setVisible(true);
    }

    private static void deleteEmployee() {
        // Add your delete employee logic here
        System.out.println("Deleting Employee...");
        JFrame temp = new  DeleteEmployee("Delete Employee");
        temp.setVisible(true);
    }

    private static void createEmployee() {
        // Add your search another employee logic here
        
        JFrame temp = new EmployeeRegistrationFrame("Employee Registration");
        temp.setVisible(true);
      System.out.println("Creating Employee...");
    }

    private static void viewStaff() {
        // Add your view staff logic here
        System.out.println("Viewing Staff...");
        JFrame temp = new ViewTableFrame("view Staff");
        temp.setVisible(true);
    }
}
