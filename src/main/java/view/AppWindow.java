package view;
import javax.swing.JFrame;

/**
 * Klasa odpowiadająca za ustawianie GUI
 * @author Fokkittah 
 */

 public class AppWindow {
    private JFrame frame;

    public AppWindow() {
        frame = new JFrame(); // creates a new JFrame
        frame.setTitle("Multithreaded Copy App"); // sets the title of the window
        frame.setSize(500, 300); // sets the initial size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the application when the window is closed
    }

    public void show() {
        frame.setVisible(true); // makes the window visible
    }
}