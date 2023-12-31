package queryConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;



class ViewTransactions extends JFrame {
    private JTextArea resultTextArea;

    public ViewTransactions(String title) {
        super(title);
        setLayout(new BorderLayout());

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton viewButton = new JButton("View Table");
        viewButton.addActionListener(e -> viewTable());
        add(viewButton, BorderLayout.SOUTH);

        this.setSize(400, 300);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setVisible(true);
    }

    private void viewTable() {
    	final String username = "eziegl4";
    	final String password = "COSC*26yaj";
    	final String url = "jdbc:mysql://triton.towson.edu:3360/?serverTimezoneEST#/"+username+"db";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM eziegl4db.FoodBill;";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                StringBuilder resultText = new StringBuilder("Table Data:\n\n");

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    resultText.append(metaData.getColumnName(i)).append("\t\t");
                }
                resultText.append("\n\n");

                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        resultText.append(resultSet.getString(i)).append("\t\t");
                    }
                    resultText.append("\n");
                }

                resultTextArea.setText(resultText.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database");
        }
    }
}

