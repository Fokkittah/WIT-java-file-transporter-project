package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.log4j.Logger;

/**
 * @author Fokkittah
 */

public class FileCopyTask implements Runnable {
    private static final Logger logger = Logger.getLogger(FileCopyTask.class);

    private Path sourceFile;
    private Path destinationPath;

    public FileCopyTask(Path sourceFile, Path destinationPath) {
        this.sourceFile = sourceFile;
        this.destinationPath = destinationPath;
    }

    @Override
    public void run() {
        try {
            logger.info("Starting copy for: " + sourceFile + " to: " + destinationPath);
            Files.copy(sourceFile, destinationPath);
            incrementCopiedFilesCount();
            logger.info("Finished copy for: " + sourceFile + " to: " + destinationPath);
        } catch (IOException e) {
            logger.error("Failed to copy file: " + sourceFile, e);
        }
    }

    private static AtomicInteger copiedFilesCount = new AtomicInteger(0);
    // private static int copiedFilesCount = 0;

    private void incrementCopiedFilesCount() {
        copiedFilesCount.incrementAndGet();
    }

    // replaced synchronized method with AtomicInteger method
    public static int getCopiedFilesCount() {
        return copiedFilesCount.get();
    }
}

