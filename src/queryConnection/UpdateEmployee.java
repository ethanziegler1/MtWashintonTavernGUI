package queryConnection;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateEmployee extends JFrame{

    private JTextField empIdTextField;
    private Connection connection;

    public UpdateEmployee(){
        try {
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws SQLException {
        

        setTitle("Employee Management System");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        empIdTextField = new JTextField(10);
        JButton checkButton = new JButton("Check Employee");
        checkButton.addActionListener(e -> checkEmployee());

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter Employee ID:"));
        panel.add(empIdTextField);
        panel.add(checkButton);

        this.getContentPane().add(panel);
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        
    }

   

    private void checkEmployee() {
        String empId = empIdTextField.getText();

        try (Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM employees WHERE employee_id = '" + empId + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                showEmployeeDetailsScreen(empId);
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showEmployeeDetailsScreen(String id) {
        this.getContentPane().removeAll(); 

        String[] jobOptions = {"Cook", "Server", "Host"};
        JComboBox<String> jobComboBox = new JComboBox<>(jobOptions);
        JTextField hoursTextField = new JTextField(10);
        JTextField salaryTextField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            try {
                submitEmployeeDetails(jobComboBox.getSelectedItem().toString(), hoursTextField.getText(), salaryTextField.getText(), id);
            } catch (SQLException e1) {
              
                e1.printStackTrace();
            }
        });

    
        JPanel panel = new JPanel();
        panel.add(new JLabel("Job Title:"));
        panel.add(jobComboBox);
        panel.add(new JLabel("Hours:"));
        panel.add(hoursTextField);
        panel.add(new JLabel("Salary:"));
        panel.add(salaryTextField);
        panel.add(submitButton);

        this.getContentPane().add(panel);
        this.revalidate();
        this.repaint();
    }

    private void submitEmployeeDetails(String jobTitle, String hours, String salary, String ID) throws SQLException {

        final String username = "eziegl4";
        final String password = "COSC*26yaj";
        final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/"+username+"db";
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "UPDATE Worker " +
                    " SET Salary = ? , HoursWorked = ? , StaffType = ?" +
                    " WHERE employee_id = ?;";
            String temp1 =""+(Math.floor(Math.random() * (100 - 10) + 10) / 100), temp2=""+(Math.floor(Math.random() * (30 - 1) + 1) / 100);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, salary);
                preparedStatement.setString(2, hours);
                preparedStatement.setString(3, jobTitle);
                preparedStatement.setString(4, ID);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(UpdateEmployee.this, "Employee Updated!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Update Employee.");
                }
            
            
            }

            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
        
        System.out.println(ex);
        
        
        }
        JOptionPane.showMessageDialog(this, "Employee details submitted.");

        this.getContentPane().removeAll();
        initialize();
    }
}