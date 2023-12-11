package queryConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class CreateTransaction extends JFrame {
    private JTextField customerIdField, nameField, tableNumField, tipsField;
    private JLabel serverIdField = new JLabel();
    private JButton submitButton;
    private shoppingcart cart = new shoppingcart();

    private String empID, totalcost, tip = "" + (((int) Math.random() * 10 + 1)), Cname, CID, TNum, SID;

    public CreateTransaction() {
        setTitle("Create Transaction");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2));

        panel.add(new JLabel("Customer ID:"));
        customerIdField = new JTextField();
        panel.add(customerIdField);

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Table Number:"));
        tableNumField = new JTextField();
        panel.add(tableNumField);

        panel.add(new JLabel("Server ID:"));
        serverIdField.setText(Main_Menu.user.getCurrentUser());
        ;
        panel.add(serverIdField);

        panel.add(new Label("Tip:"));
        tipsField = new JTextField();
        panel.add(tipsField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitData();
            }
        });
        panel.add(submitButton);

        add(panel);
        setVisible(true);
    }

    private void submitData() {
        String customerId = customerIdField.getText();
        String name = nameField.getText();
        String tableNum = tableNumField.getText();
        String serverId = serverIdField.getText();
        String tip = tipsField.getText();

        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Table Number: " + tableNum);
        System.out.println("Server ID: " + serverId);
        Cname = name;
        TNum = "" + tableNum;
        SID = serverId;
        CID = customerId;
        tip = tipsField.getText();
        // Open the second window
        
        new FoodMenuWindow();
        new ShoppingCartWindow();
        this.setVisible(false);
    }

    private void createCustomer(String cid, String name, String tbnum, String sid) {
        final String username = "eziegl4";
        final String password = "COSC*26yaj";
        final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/" + username + "db";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO eziegl4db.Customer (CustomerID, Name, TableNum, ServerID) VALUES(?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, cid);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, tbnum);
                preparedStatement.setString(4, sid);

                int resultSet = preparedStatement.executeUpdate();

                if (resultSet > 0) {
                    JOptionPane.showMessageDialog(this, "Employee added to database successfully.");

                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add employee to database.");
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database.");
        }
    }

    private class FoodMenuWindow extends JFrame {
        private JTable foodTable;
        private DefaultTableModel food;
        private JButton addToCartButton;

        public FoodMenuWindow() {
            setTitle("Food Menu");
            setSize(600, 400);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            // Assuming you have a method to retrieve data from the database
            food = new DefaultTableModel();
            food.addColumn("Price");
            food.addColumn("Category");
            food.addColumn("Description");
            food.addColumn("Item ID");

            foodTable = new JTable(food);
            displayFoodFromDatabase();
            foodTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {

                        cart.addFood(new item(SID, Cname, tip, CID));

                    }
                }
            });

            addToCartButton = new JButton("Add to Cart");
            addToCartButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    addToCart();

                }
            });

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JScrollPane(foodTable), BorderLayout.CENTER);
            panel.add(addToCartButton, BorderLayout.SOUTH);

            add(panel);
            setVisible(true);
        }

        private void displayFoodFromDatabase() {
            DefaultTableModel model = (DefaultTableModel) foodTable.getModel();
            model.setRowCount(0);

            // Database connection parameters
            final String username = "eziegl4";
            final String password = "COSC*26yaj";
            final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/" + username + "db";

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String query = "SELECT * FROM eziegl4db.Food";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            String price = resultSet.getString("price");
                            String category = resultSet.getString("category");
                            String description = resultSet.getString("description");
                            String itemId = resultSet.getString("ItemID");

                            model.addRow(new Object[] { price, category, description, itemId });
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error connecting to the database.");
            }

        }

        private void addToCart() {
            int selectedRow = foodTable.getSelectedRow();
            if (selectedRow != -1) {
                Vector<Object> rowData = new Vector<>();
                for (int i = 0; i < food.getColumnCount(); i++) {
                    rowData.add(food.getValueAt(selectedRow, i));
                }
                // Add the selected item to the cart table
                cartTableModel.addRow(rowData);
            }
            cart.addFood(new item(
                    (String) food.getValueAt(selectedRow, 0),
                    (String) food.getValueAt(selectedRow, 1),
                    (String) food.getValueAt(selectedRow, 2),
                    (String) food.getValueAt(selectedRow, 3)));
            System.out.println(cart);
        }
    }

    private DefaultTableModel cartTableModel;

    private class ShoppingCartWindow extends JFrame {
        private JTable cartTable;
        private JButton removeFromCartButton, submitButton;

        public ShoppingCartWindow() {
            setTitle("Shopping Cart");
            setSize(400, 300);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            Vector<String> columnNames = new Vector<>();
            columnNames.add("Price");
            columnNames.add("Category");
            columnNames.add("Item Name");
            columnNames.add("ItemID");

            cartTableModel = new DefaultTableModel(null, columnNames);
            cartTable = new JTable(cartTableModel);
            cartTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        removeFromCart();
                    }
                }
            });

            removeFromCartButton = new JButton("Remove from Cart");
            removeFromCartButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    removeFromCart();
                }
            });

            submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Implement code to handle submission of the shopping cart
                    submitCart();
                }
            });

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JScrollPane(cartTable), BorderLayout.CENTER);
            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(removeFromCartButton);
            buttonPanel.add(submitButton);
            panel.add(buttonPanel, BorderLayout.SOUTH);

            add(panel);
            setVisible(true);
        }

        private void removeFromCart() {
            int selectedRow = cartTable.getSelectedRow();
            if (selectedRow != -1) {
                cart.removeFromCart((String) cartTableModel.getValueAt(selectedRow, 2));
                cartTableModel.removeRow(selectedRow);

            }
        }

        private void submitCart() {

            // Implement code to handle the submission of the shopping cart
            // For example, you can print the items to the console
            for (int i = 0; i < cartTableModel.getRowCount(); i++) {
                // System.out.println("Item ID: " + cartTableModel.getValueAt(i, 0));
                // System.out.println("Item Name: " + cartTableModel.getValueAt(i, 3));
                // System.out.println("Price: " + cartTableModel.getValueAt(i, 1));
            }
            // Close the application after submission

            final String username = "eziegl4";
            final String password = "COSC*26yaj";
            final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/" + username + "db";

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String query = "INSERT INTO eziegl4db.FoodBill ('BillID', 'TotalPrice','ServerID','CID') VALUES(?,?,?,?); INSERT INTO EarningsPayment('TID','TotalCost','Tip','ServerID') VAULES (?,?,?,?);";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    String tb = getBID();
                    preparedStatement.setString(1, tb);
                    preparedStatement.setString(2, (cart.getTotal()+tip));
                    preparedStatement.setString(3, SID);
                    preparedStatement.setString(4, CID);

                    preparedStatement.setString(5,tb );
                    preparedStatement.setString(6, (cart.getTotal()+tip));
                    preparedStatement.setString(7, tip);
                    preparedStatement.setString(8, SID);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error connecting to the database.");
            }
            createCustomer(CID, Cname, TNum, SID);
            this.dispose();
        }

        public String getBID(){
            int num = (int)(Math.random()*10000)+1;
            final String username = "eziegl4";
            final String password = "COSC*26yaj";
            final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/" + username + "db";
            
                
           
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String query = "SELECT count(*) FROM eziegl4db.FoodBill;";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

                    if (resultSet.next()) {
                        num = resultSet.getInt(1)+1;
                        System.out.println(num);
                         
                    }

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error connecting to the database.");
            } 

           return ""+num;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CreateTransaction();
            }
        });
    }
}
