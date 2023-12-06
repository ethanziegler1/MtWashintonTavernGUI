package queryConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;



class EmployeeRegistrationFrame extends JFrame {
    private JTextField ssnField, nameField, hoursField, salaryField, phoneNumberField;
    private JComboBox<String> staffTypeComboBox;
    private JLabel employeeIdLabel, employeeIdValueLabel;

    private Set<String> employeeIdSet = new HashSet<>();

    public EmployeeRegistrationFrame(String title) {
        super(title);
        setLayout(new GridLayout(9, 2, 5, 5));
        this.setSize(400, 300);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        add(new JLabel("SSN:"));
        ssnField = new JTextField();
        add(ssnField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Staff Type:"));
        String[] staffTypes = {"Cook", "Server", "Host"};
        staffTypeComboBox = new JComboBox<>(staffTypes);
        add(staffTypeComboBox);

        add(new JLabel("Hours:"));
        hoursField = new JTextField();
        add(hoursField);

        add(new JLabel("Salary:"));
        salaryField = new JTextField();
        add(salaryField);

        add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField();
        add(phoneNumberField);

        add(new JLabel("Employee ID:"));
        employeeIdLabel = new JLabel();
        generateUniqueEmployeeId();
        add(employeeIdLabel);

        add(new JLabel("")); 

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String EmployeeID = employeeIdLabel.getText();
            	String SSN = ssnField.getText();
            	String HoursWorked = hoursField.getText();
            	String Name = nameField.getText();
            	String StaffType = staffTypeComboBox.getItemAt(2);
            	String Salary = salaryField.getText();
                JOptionPane.showMessageDialog(EmployeeRegistrationFrame.this, "Employee Registered!");
                addEmployeeToDatabase(EmployeeID, SSN, HoursWorked, Name, StaffType, Salary);
            }
        });
        add(registerButton);
    }

    private void generateUniqueEmployeeId() {
        Random random = new Random();
        while (true) {
            String employeeId = String.format("%09d", random.nextInt(1000000000));
            if (!employeeIdSet.contains(employeeId)) {
                employeeIdSet.add(employeeId);
                employeeIdLabel.setText(employeeId);
                break;
            }
        }
    }
    public static void addEmployeeToDatabase(empID,SSN,hours,name,type,salary) {
            // JDBC database connection parameters
            final String username = "eziegl4";
        	final String password = "COSC*26yaj";
        	final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/"+username+"db";

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String sql = "DELETE FROM eziegl4db.Worker WHERE employee_id = ?";
                String sql = "INSERT INTO eziegl4db.Worker (EmployeeID, SSN, Name, StaffType, Salary, PhoneNumbe) VALUES (";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, employeeId);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error connecting to the database");
            }
        }
}
