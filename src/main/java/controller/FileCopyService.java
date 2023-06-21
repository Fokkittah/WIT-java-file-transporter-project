package controller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import model.FileHandler;

/**
 * @author Fokkittah
 */

public class FileCopyService {
    private static final Logger logger = Logger.getLogger(FileCopyService.class);

    private FileHandler fileHandler;
    private ThreadManager threadManager;

    public FileCopyService(FileHandler fileHandler, ThreadManager threadManager) {
        this.fileHandler = fileHandler;
        this.threadManager = threadManager;
    }

    public void copyFiles(String fileMask) {
        try{
            logger.info("Starting to fill file path map");
            fileHandler.fillFilePathMap(fileMask); // Added filemask functionality from AppWindow
            logger.info("Starting to create destination directories");
            fileHandler.createDestinationDirectories();
            
            Map<Path, Path> sourceDestinationMap = fileHandler.getFilePathMap();
            Set<Path> filesToCopy = sourceDestinationMap.keySet();

            // Create tasks
            List<FileCopyTask> tasks = new ArrayList<>();
            for (Path file : filesToCopy) {
                tasks.add(new FileCopyTask(file, sourceDestinationMap.get(file)));
            }

            // Pass the tasks to the ThreadManager for execution
            threadManager.executeTasks(tasks);
        } catch (Exception e) {
            // Log the exception
            logger.error("Something went wrong",e);
        }
    }
}