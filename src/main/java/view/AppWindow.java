package view;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import java.awt.*;
import javax.swing.JTextField;


/**
 * Class responsible for setting up the GUI
 * @author Fokkittah 
 */

 public class AppWindow {

    private JFrame frame;
    private JButton sourceButton;
    private JButton destinationButton;
    private JButton startButton;
    private JTextField fileMaskField;
    private JLabel statusLabel;

    public AppWindow() {
        frame = new JFrame(); // creates a new JFrame
        frame.setTitle("Multithreaded Copy App"); // sets the title of the window
        frame.setSize(500, 300); // sets the initial size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the application when the window is closed

        // The above is just a simple window
        // ----------------------------
        // Adding functionality below...

        frame.setLayout(new BorderLayout()); // use border layout

        JPanel northButtonPanel = new JPanel(); // create a new panel for the buttons
        northButtonPanel.setLayout(new BoxLayout(northButtonPanel, BoxLayout.LINE_AXIS)); // set the layout to line up components from left to right

        sourceButton = new JButton("Select Source"); // create source button
        destinationButton = new JButton("Select Destination"); // create destination button
        startButton = new JButton("Start"); // create start button
        fileMaskField = new JTextField(".*");

        northButtonPanel.add(sourceButton); // add source button to the panel
        northButtonPanel.add(destinationButton); // add destination button to the panel
        northButtonPanel.add(Box.createHorizontalGlue()); // add "glue" to take up extra space
        northButtonPanel.add(fileMaskField);
        northButtonPanel.add(startButton); // add start button to the panel

        frame.add(northButtonPanel, BorderLayout.NORTH); // add the button panel to the north

        // Add a separator
        JPanel separatorPanel = new JPanel(new BorderLayout());
        separatorPanel.add(new JSeparator(), BorderLayout.NORTH);
        frame.add(separatorPanel, BorderLayout.CENTER);

        // Add a separator and panel to the bottom
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.LIGHT_GRAY); // set the background color of the bottom panel
        
        // Add a separator to the top of the bottom panel
        bottomPanel.add(new JSeparator(), BorderLayout.NORTH);

        // Add a status label to the right of the bottom panel
        statusLabel = new JLabel("Waiting for source input...");
        statusLabel.setHorizontalAlignment(JLabel.RIGHT);
        bottomPanel.add(statusLabel, BorderLayout.EAST);

        frame.add(bottomPanel, BorderLayout.SOUTH); // add the bottom panel to the south
    }

    /**
     * Method that sets up an object that listens to actions performed on the screen
     * @param actionListener
     * @author Kacper Fugas
     */
    public void setUpActionListener(ActionListener actionListener){
        sourceButton.setActionCommand("sourceButtonClicked");
        destinationButton.setActionCommand("destinationButtonClicked");
        startButton.setActionCommand("startButtonClicked");

        sourceButton.addActionListener(actionListener);
        destinationButton.addActionListener(actionListener);
        startButton.addActionListener(actionListener);
    }

    public void show() {
        frame.setVisible(true); // makes the window visible
    }

    public JLabel getStatusLabel(){
        return statusLabel;
    }

    public JFrame getFrame(){
        return frame;
    }

    public String getFileMask() {
        return fileMaskField.getText();
    }
}