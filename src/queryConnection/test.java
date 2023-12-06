package queryConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test {

    public static void main(String[] args) {
        // Create and set up the main window
        JFrame mainWindow = new JFrame("Main Window");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(300, 200);

        // Create a panel to hold components
        JPanel mainPanel = new JPanel();

        // Create a button
        JButton openButton = new JButton("Open Second Window");

        // Add an ActionListener to the button
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the button is pressed, open the second window
                openSecondWindow();
            }
        });

        // Add the button to the panel
        mainPanel.add(openButton);

        // Add the panel to the main window
        mainWindow.getContentPane().add(mainPanel);

        // Display the main window
        mainWindow.setVisible(true);
    }

    private static void openSecondWindow() {
        // Create and set up the second window
        JFrame secondWindow = new JFrame("Second Window");
        secondWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close to only close the second window
        secondWindow.setSize(300, 200);

        // Display the second window
        secondWindow.setVisible(true);
    }
}