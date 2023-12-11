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
                String phoneNumber = phoneNumberField.getText();
                
                addEmployeeToDatabase(EmployeeID, SSN, HoursWorked, Name, StaffType, Salary, phoneNumber);

            }
        });
        add(registerButton);
    }

    private void generateUniqueEmployeeId() {
        Random random = new Random();
        while (true) {
            String employeeId = String.format("%09d", random.nextInt(999999999));
            if (!employeeIdSet.contains(employeeId)) {
                employeeIdSet.add(employeeId);
                employeeIdLabel.setText(employeeId);
                break;
            }
        }
    }
    public void addEmployeeToDatabase(String empID,String SSN, String hours, String name, String type,String salary,String phone) {
            // JDBC database connection parameters
            final String username = "eziegl4";
        	final String password = "COSC*26yaj";
        	final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/"+username+"db";
            
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String sql = "INSERT INTO eziegl4db.EarningsPayment (TID, TotalCost, Tip, ServerID) VALUES (?, ?, ?,?);INSERT INTO eziegl4db.Worker (EmployeeID, SSN, HoursWorked, Name, StaffType, Salary, PhoneNumber) VALUES (?,?,?,?,?,?,?);";
                String temp1 =""+(Math.floor(Math.random() * (100 - 10) + 10) / 100), temp2=""+(Math.floor(Math.random() * (30 - 1) + 1) / 100);
                
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, empID);
                    preparedStatement.setString(2, temp1);
                    preparedStatement.setString(3, temp2);
                    preparedStatement.setString(4, empID);

                    preparedStatement.setString(5, empID);
                    preparedStatement.setString(6, SSN);
                    preparedStatement.setString(7, hours);
                    preparedStatement.setString(8, name);
                    preparedStatement.setString(9, type);
                    preparedStatement.setString(10, salary);
                    preparedStatement.setString(11, phone);

                int rowsAffected = preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(EmployeeRegistrationFrame.this, "Employee Registered!");
                //this.dispose();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Employee added to database successfully.");
                   
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add employee to database.");
                }
                }

                
            } catch (SQLException ex) {
                
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error connecting to the database");
            
            System.out.println(ex);
            System.out.println(empID);
            
            }
        }
}
