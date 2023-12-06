package queryConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;



class ViewTableFrame extends JFrame {
    private JTextArea resultTextArea;

    public ViewTableFrame(String title) {
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
    }

    private void viewTable() {
        // JDBC database connection parameters
        String url = "jdbc:mysql://localhost:3306/your_database"; // Update with your database URL
        String username = "your_username";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String tableName = "your_table_name"; // Update with your table name

            String sql = "SELECT * FROM " + tableName;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                StringBuilder resultText = new StringBuilder("Table Data:\n");

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Append column names
                for (int i = 1; i <= columnCount; i++) {
                    resultText.append(metaData.getColumnName(i)).append("\t");
                }
                resultText.append("\n");

                // Append row data
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        resultText.append(resultSet.getString(i)).append("\t");
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

