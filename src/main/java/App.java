/**
 * @author Fokkittah
 */

import javax.swing.SwingUtilities;

import controller.AppWindowController;

/**
 * Main class for running the application.
 */
public class App {
    /**
     * Main method to start the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Start the application
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    AppWindowController app = new AppWindowController();
                    app.setUpAndShowWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
