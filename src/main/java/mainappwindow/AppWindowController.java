package mainappwindow;

import filehandler.FileHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import view.FileCopyTask;

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

        if (fileHandler.getSourceFilesDirectory() == null || fileHandler.getDestinationDirectory() == null) {
            JOptionPane.showMessageDialog(appWindow.getFrame(), "Please select source and destination directories", "Error", JOptionPane.ERROR_MESSAGE);
        } else{
            // Perform file copying logic here

            try{
                fileHandler.fillFilePathMap("dummy");
                Map<Path, Path> sourceDestinationMap = fileHandler.getFilePathMap();
                Set<Path> filesToCopy = sourceDestinationMap.keySet();
                logger.info("Chosen files:");
                for (Path path : filesToCopy){
                    logger.info(path);
                }

                //Create destination directory tree
                fileHandler.createDestinationDirectories();

                // Create a thread pool with the specified number of threads
                int numThreads = 4;
                ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

                for (Path file : filesToCopy) {
                    executorService.execute(new FileCopyTask(file, sourceDestinationMap.get(file)));
                }

                executorService.shutdown();

                try {
                    executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int copiedFilesCount = FileCopyTask.getCopiedFilesCount();
                appWindow.getStatusLabel().setText("Copied files count: " + copiedFilesCount);


            }catch (Exception e){
                //TODO poprawić
                logger.error("Something went wrong",e);
            }


        }

    }
}
