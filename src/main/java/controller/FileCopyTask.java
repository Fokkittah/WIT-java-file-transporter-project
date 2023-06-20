package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

public class FileCopyTask implements Runnable {
    private Path sourceFile;
    private Path destinationPath;

    public FileCopyTask(Path sourceFile, Path destinationPath) {
        this.sourceFile = sourceFile;
        this.destinationPath = destinationPath;
    }

    @Override
    public void run() {
        try {
            Files.copy(sourceFile, destinationPath);
            incrementCopiedFilesCount();
        } catch (IOException e) {
            e.printStackTrace();
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

