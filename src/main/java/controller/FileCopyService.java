package controller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.FileHandler;

/**
 * @author Fokkittah
 */

public class FileCopyService {
    private FileHandler fileHandler;
    private ThreadManager threadManager;

    public FileCopyService(FileHandler fileHandler, ThreadManager threadManager) {
        this.fileHandler = fileHandler;
        this.threadManager = threadManager;
    }

    public void copyFiles() {
        try{
            fileHandler.fillFilePathMap("dummy");
            Map<Path, Path> sourceDestinationMap = fileHandler.getFilePathMap();
            Set<Path> filesToCopy = sourceDestinationMap.keySet();

            // Create tasks
            List<FileCopyTask> tasks = new ArrayList<>();
            for (Path file : filesToCopy) {
                tasks.add(new FileCopyTask(file, sourceDestinationMap.get(file)));
            }

            // Pass the tasks to the ThreadManager for execution
            threadManager.executeTasks(tasks);
            fileHandler.createDestinationDirectories();
        } catch (Exception e) {
            // Log the exception
            // logger.error("Something went wrong",e);
        }
    }
}