package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


import model.FileHandler;
import view.AppWindow;

/**
 * Controller class that handles actions performed on the AppWindow view and communicates them to other classes.
 *
 * @author Kacper Fugas, Fokkittah
 */

/**
 * Controller class that handles actions performed on the AppWindow view and communicates them to other classes.
 */

public class AppWindowController implements ActionListener {
    private static final int NUM_THREADS = 4;

    private final FileHandler fileHandler;
    private final AppWindow appWindow;
    private final FileCopyService fileCopyService;
    private final ThreadManager threadManager;

    // private static final Logger logger = LogManager.getLogger(AppWindowController.class);
    
    /**
     * Constructor for AppWindowController.
     */

    public AppWindowController(){
        this.fileHandler = new FileHandler();
        this.appWindow = new AppWindow();
        this.threadManager = new ThreadManager(NUM_THREADS);
        this.fileCopyService = new FileCopyService(fileHandler, threadManager);
    }

    /**
     * Sets up the window and shows it.
     */
    
    public void setUpAndShowWindow(){
        appWindow.setUpActionListener(this);
        appWindow.show();
    }

    /**
     * Method called when appWindow triggers an event.
     * Delegates actions to corresponding methods
     * @param event event triggered on the screen
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("sourceButtonClicked")) {
            onSourceButtonClicked();
        }else if (event.getActionCommand().equals("destinationButtonClicked")){
            onDestinationButtonClicked();
        }else if(event.getActionCommand().equals("startButtonClicked")){
            onStartButtonClicked();
        }
    }
    
    /**
     * Handles the destination button clicked event.
     */
    
    public void onDestinationButtonClicked(){
        fileHandler.chooseDestinationDirectory();
    }
    
    /**
     * Handles the source button clicked event.
     */

    public void onSourceButtonClicked(){
        fileHandler.chooseSourceFilesDirectory();
    }
    
    /**
     * Handles the start button clicked event.
     */
    
    public void onStartButtonClicked() {
        if (fileHandler.getSourceFilesDirectory() == null || fileHandler.getDestinationDirectory() == null) {
            JOptionPane.showMessageDialog(appWindow.getFrame(), "Please select source and destination directories", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String fileMask = appWindow.getFileMask();
            fileCopyService.copyFiles(fileMask);
            int copiedFilesCount = FileCopyTask.getCopiedFilesCount();
            appWindow.getStatusLabel().setText("Copied files count: " + copiedFilesCount);
        }
    }
}
