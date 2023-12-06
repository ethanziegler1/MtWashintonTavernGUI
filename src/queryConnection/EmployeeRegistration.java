package queryConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;



class EmployeeRegistrationFrame extends JFrame {
    private JTextField ssnField, firstNameField, lastNameField, hoursField, salaryField, phoneNumberField;
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

        add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        add(lastNameField);

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
               
                JOptionPane.showMessageDialog(EmployeeRegistrationFrame.this, "Employee Registered!");
                generateUniqueEmployeeId(); 
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
}
