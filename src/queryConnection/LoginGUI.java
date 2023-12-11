package queryConnection;

import javax.swing.*;

import netscape.javascript.JSObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginGUI extends JFrame {

    private JTextField idField;
    private String spid;


    public LoginGUI() {
     
        setTitle("Employee Login");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

     
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

 
        JLabel idLabel = new JLabel("Employee ID:");
        idField = new JTextField(9);
        
        
      
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateLogin(idField.getText())) {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Login successful!");
                    windowSwap();
                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Invalid Employee ID or SSN", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        
    
        
        panel.add(new JLabel()); 
        panel.add(loginButton);

        add(panel);

    }
    

    private boolean validateLogin(String employeeId) {
    
        String employeeID = idField.getText();
      
            final String username = "eziegl4";
    	final String password = "COSC*26yaj";
    	final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/"+username+"db";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM eziegl4db.Worker WHERE EmployeeID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, employeeId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Employee found
                        String name = resultSet.getString("EmployeeID");
                        spid =name;
                        return true;
                    } else {
                        // Employee not found
                        JOptionPane.showMessageDialog(this, "Login Invalid");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
        }
        
        return employeeID.length() <= 9;
    }

    public static void main(String[] args) {
       
        LoginGUI loginGUI = new LoginGUI();

     
        loginGUI.setVisible(true);
    }
    
    private static void employeeManagementApp() {
        
        JFrame temp = new EmployeeManagementApp("Employee Management");
        temp.setVisible(true);
      System.out.println("Opening Management App...");
    }

    private void windowSwap(){
        new Main_Menu().user.setCurrentUser(spid);;
        this.dispose();
    }
}
