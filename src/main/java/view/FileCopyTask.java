
/**
 * @author Mateusz Marchel
 */

package view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

    private static int copiedFilesCount = 0;

    private static synchronized void incrementCopiedFilesCount() {
        copiedFilesCount++;
    }

    public static synchronized int getCopiedFilesCount() {
        return copiedFilesCount;
    }
}

