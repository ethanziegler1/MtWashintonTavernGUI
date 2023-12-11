package queryConnection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customer_Menu extends JFrame {
    public Customer_Menu() {
        setTitle("Customer");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton createTransactionButton = new JButton("Create Transaction");
        JButton searchTransactionsButton = new JButton("View all Transactions");
     
     

        JPanel panel = new JPanel();

        panel.add(createTransactionButton);
        panel.add(searchTransactionsButton);
      
      

        add(panel);

        createTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
     
                System.out.println("Create Transaction button clicked");
                new CreateTransaction();
            }
        });

        searchTransactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
          
                System.out.println("View all transactions clicked");
                new ViewTransactions("View Transactions");
            }
        });

  

   
        setSize(300, 200);

  
        setVisible(true);
    }

    public static void main(String[] args) {
      
        Customer_Menu customer = new Customer_Menu();
    }
}
