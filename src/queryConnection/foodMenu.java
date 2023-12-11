package queryConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class foodMenu extends JFrame {
    public foodMenu() {
        setTitle("Food Menu");
        setSize(400, 80);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JButton showItemsButton = new JButton("Show Items");
        JButton addItemButton = new JButton("Add Item");
        JButton deleteItemButton = new JButton("Delete Item");

        setLayout(new FlowLayout());

        add(showItemsButton);
        add(addItemButton);
        add(deleteItemButton);

        showItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
 
                new showFood();
            }
        });

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new addFood();
            }
        });

        deleteItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteFood("Delete Food");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new foodMenu().setVisible(true);
            }
        });
    }
}
