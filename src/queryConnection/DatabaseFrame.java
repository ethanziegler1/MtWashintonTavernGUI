package queryConnection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DatabaseFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DatabaseFrame frame = new DatabaseFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   
    public DatabaseFrame() {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	} catch (ClassNotFoundException e) {
    		System.out.println(e);
    	}
    	final String ID = "eziegl4";
    	final String PW = "COSC*26yaj";
    	final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/"+ID+"db";
    	try {
    		Connection con = DriverManager.getConnection(SERVER, ID, PW);
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("Select * from eziegl4db.Food");
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 75, 750, 750);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.YELLOW);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Mt. Washington Tavern Database");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(75, 50, 1000, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(250, 250, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);


        JLabel Entity = new JLabel("Entity");
        Entity.setBackground(Color.BLACK);
        Entity.setForeground(Color.BLACK);
        Entity.setFont(new Font("Tahoma", Font.PLAIN, 31));
        Entity.setBounds(150, 250, 193, 52);
        contentPane.add(Entity);
        
        JLabel Attribute = new JLabel("Attribute");
        Attribute.setBackground(Color.BLACK);
        Attribute.setForeground(Color.BLACK);
        Attribute.setFont(new Font("Tahoma", Font.PLAIN, 31));
        Attribute.setBounds(125, 350, 193, 52);
        contentPane.add(Attribute);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(250, 350, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);
        
        btnNewButton = new JButton("Submit Query");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(285, 500, 200, 73);
        btnNewButton.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
                String query = textField.getText();
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo",
                        "root", "root");

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select name, password from student where name=? and password=?");

                    st.setString(1, query);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(btnNewButton, "Query Sent");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Query Invalid");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });
        JLabel queryResult = new JLabel();
        
        
        queryResult.setBackground(Color.BLACK);
        queryResult.setForeground(Color.BLACK);
        queryResult.setFont(new Font("Tahoma", Font.PLAIN, 31));
        queryResult.setBounds(100, 100, 500, 500);
        contentPane.add(queryResult);
        
        contentPane.add(btnNewButton);

        label = new JLabel("");
        label.setBounds(0, 5, 1500, 1000);
        label.setAlignmentX(50);
        contentPane.add(label);
    	} catch(SQLException e) {
    		System.err.println(e);
    	}
    }
}