package mainappwindow;

import filehandler.FileHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.Set;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Controller class that handles actions performed on the AppWindow view and communicates them to other classes.
 *
 * @author Kacper Fugas, ...
 */
public class AppWindowController implements ActionListener {
    private final FileHandler fileHandler;
    private final AppWindow appWindow;
    private static final Logger logger = LogManager.getLogger(AppWindowController.class);

    public AppWindowController(){
        fileHandler = new FileHandler();
        appWindow = new AppWindow();
    }

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

    public void onDestinationButtonClicked(){
        fileHandler.chooseDestinationDirectory();
    }
    public void onSourceButtonClicked(){
        fileHandler.chooseSourceFilesDirectory();
    }
    public void onStartButtonClicked() {
        try{
            fileHandler.fillSourceFiles("dummy");
            Set<Path> filesToCopy = fileHandler.getSourceFiles();
            logger.info("Chosen files:");
            for (Path path : filesToCopy){
                logger.info(path);
            }
        }catch (Exception e){
            //TODO poprawić
            logger.error("Something went wrong",e);
        }

        // TODO walidacja
        // TODO kopiowanie
    }
}
