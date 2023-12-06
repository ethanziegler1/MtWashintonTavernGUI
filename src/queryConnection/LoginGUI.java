package queryConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {

    private JTextField idField;
    private JPasswordField ssnField;

    public LoginGUI() {
     
        setTitle("Employee Login");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

 
        JLabel idLabel = new JLabel("Employee ID:");
        idField = new JTextField(9);
        JLabel ssnLabel = new JLabel("SSN:");
        ssnField = new JPasswordField(9);
        
      
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateLogin()) {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Invalid Employee ID or SSN", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(ssnLabel);
        panel.add(ssnField);
        
        panel.add(new JLabel()); 
        panel.add(loginButton);

        add(panel);

    }

    private boolean validateLogin() {
    
        String employeeID = idField.getText();
        String ssn = new String(ssnField.getPassword());

        
        return employeeID.length() == 9 && ssn.length() == 9;
    }

    public static void main(String[] args) {
       
        LoginGUI loginGUI = new LoginGUI();

     
        loginGUI.setVisible(true);
    }
}
