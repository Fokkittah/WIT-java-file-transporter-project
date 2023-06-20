/**
 * @author Fokkittah
 */

import javax.swing.SwingUtilities;

import controller.AppWindowController;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    AppWindowController app= new AppWindowController();
                    app.setUpAndShowWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}