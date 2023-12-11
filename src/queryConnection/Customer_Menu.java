package queryConnection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customer_Menu extends JFrame {
    public Customer_Menu() {
        // Set the title of the JFrame
        setTitle("Customer");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons
        JButton createTransactionButton = new JButton("Create Transaction");
        JButton searchTransactionsButton = new JButton("View all Transactions");
     
        JButton lookupIdButton = new JButton("Lookup ID");

        // Create a panel to hold the buttons
        JPanel panel = new JPanel();

        // Add buttons to the panel
        panel.add(createTransactionButton);
        panel.add(searchTransactionsButton);
      
        panel.add(lookupIdButton);

        // Add the panel to the content pane of the frame
        add(panel);

        // Add action listeners to the buttons
        createTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Create Transaction button click
                System.out.println("Create Transaction button clicked");
                new CreateTransaction();
            }
        });

        searchTransactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Search Transactions button click
                System.out.println("View all transactions clicked");
                new ViewTransactions("View Transactions");
            }
        });

        

        lookupIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Lookup ID button click
                System.out.println("Lookup ID button clicked");
            }
        });

        // Set the size of the frame
        setSize(300, 200);

        // Set the frame to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of Customer
        Customer_Menu customer = new Customer_Menu();
    }
}
