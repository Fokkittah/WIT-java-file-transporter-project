package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Klasa odpowiadająca za ustawianie GUI
 * @author Fokkittah 
 */

 public class AppWindow {

    private JFrame frame;
    private JButton sourceButton;
    private JButton destinationButton;
    private JButton startButton;

    public AppWindow() {
        frame = new JFrame(); // creates a new JFrame
        frame.setTitle("Multithreaded Copy App"); // sets the title of the window
        frame.setSize(500, 300); // sets the initial size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the application when the window is closed

        // The above is just a simple window
        // ----------------------------
        // Adding functionality below...

        frame.setLayout(new BorderLayout()); // use border layout

        JPanel buttonPanel = new JPanel(); // create a new panel for the buttons

        sourceButton = new JButton("Select Source"); // create source button
        destinationButton = new JButton("Select Destination"); // create destination button
        startButton = new JButton("Start"); // create start button

        buttonPanel.add(sourceButton); // add source button to the panel
        buttonPanel.add(destinationButton); // add destination button to the panel
        buttonPanel.add(startButton); // add start button to the panel

        frame.add(buttonPanel, BorderLayout.SOUTH); // add the panel to the frame at the bottom

    }

    public void show() {
        frame.setVisible(true); // makes the window visible
    }
}