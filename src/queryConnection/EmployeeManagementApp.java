package queryConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import queryConnection.*;


public class EmployeeManagementApp extends JFrame {
    public static void main(String[] args) {
       
            new EmployeeManagementApp("Employee Management");
           
        
    }



    private static JLabel resultLabel;

    public EmployeeManagementApp(String title) {
        
        setTitle(title);
        setLayout(new GridLayout(2, 2, 5, 5));

        resultLabel = new JLabel();

        JButton searchButton = new JButton("Search Employee");
        searchButton.addActionListener(e -> searchEmployee());
        add(searchButton);

        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.addActionListener(e -> deleteEmployee());
        add(deleteButton);

        JButton createEmp = new JButton("Create Employee");
        createEmp.addActionListener(e -> createEmployee());
        add(createEmp);

        JButton viewStaffButton = new JButton("View Staff");
        viewStaffButton.addActionListener(e -> viewStaff());
        add(viewStaffButton);

        add(resultLabel);
        setSize(400, 150);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
    }

    private static void searchEmployee() {
        System.out.println("Searching Employee...");
        JFrame temp = new EmployeeSearchFrame("Employee Search");
        temp.setVisible(true);
    }

    private static void deleteEmployee() {
        System.out.println("Deleting Employee...");
        JFrame temp = new  DeleteEmployee("Delete Employee");
        temp.setVisible(true);
    }

    private static void createEmployee() {
      
        JFrame temp = new EmployeeRegistrationFrame("Employee Registration");
        temp.setVisible(true);
      System.out.println("Creating Employee...");
    }

    private static void viewStaff() {
        System.out.println("Viewing Staff...");
        JFrame temp = new ViewTableFrame("view Staff");
        temp.setVisible(true);
    }
}
