package filehandler;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Class that handles file paths to be copied
 * and the target destination
 *
 * @author Kacper Fugas
 */
/**
 * Class that handles file-related operations.
 */

public class FileHandler {
    private String destinationDirectory;
    private String sourceFilesDirectory;
    private Map<Path, Path> filePathMap;
    private Set<Path> directories;
    private static final Logger logger = LogManager.getLogger(FileHandler.class);
    private Path sourceDirectory;
    public Path getSourceDirectory() {
        return sourceDirectory;
    }
    public void setSourceDirectory(Path sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }
    /**
     * Methods that fill the Map with source files from chosen directory and corresponding new paths in target directory
     * @param matchRegex
     */
    public void fillFilePathMap(String matchRegex) throws IOException {
        filePathMap = new HashMap<>();
        directories = new TreeSet<>();
        fillFilePathMap(Path.of(sourceFilesDirectory), Path.of(destinationDirectory), matchRegex);
    }
    
    /**
     * Recursively traverses the source directory and populates the 'filePathMap' with source-destination path pairs.
     * Only files that match the specified regex pattern are added to the map.
     *
     * @param sourceDir      The source directory to traverse.
     * @param destinationDir The destination directory corresponding to the source directory.
     * @param matchRegex     The regular expression pattern to match against file names.
     * @throws IOException If an I/O error occurs during directory traversal.
     */
    
    private void fillFilePathMap(Path sourceDir, Path destinationDir, String matchRegex) throws IOException {
        try(DirectoryStream<Path> sourceFilesStream = Files.newDirectoryStream(sourceDir)){
            for (Path sourcePath : sourceFilesStream){

                //calculating destination path for current file
                Path destinationPath = destinationDir.resolve(sourceDir.relativize(sourcePath));

                if(sourcePath.toFile().isDirectory()) {
                    directories.add(destinationPath);
                    fillFilePathMap(sourcePath, destinationDir.resolve(sourceDir.relativize(sourcePath)), matchRegex);
                }else{
                    if(true) { //TODO if regex matches
                        filePathMap.put(sourcePath, destinationPath);
                    }
                }
            }
        }
    }
    /**
     * Must be called to create target directories before copying of files
     */
    public void createDestinationDirectories() throws IOException {
        if(directories == null) return;
        for(Path directory : directories) {
            Files.createDirectories(directory);
        }
    }

    /**
     * Opens a file chooser dialog to select the source directory for files.
     * Sets the selected source directory to the 'sourceFilesDirectory' variable.
     */
    
    public void chooseSourceFilesDirectory(){
        JFileChooser sourceChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        sourceChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        sourceChooser.setAcceptAllFileFilterUsed(false);
        if(sourceChooser.showDialog(null,"Select Directory") == 0){
            sourceFilesDirectory = sourceChooser.getSelectedFile().toString();
        }

        if(sourceFilesDirectory != null) logger.info("chose source directory: " + sourceFilesDirectory);

    }
    
    /**
     * Opens a file chooser dialog to select the destination directory for files.
     * Sets the selected destination directory to the 'destinationDirectory' variable.
     */

    public void chooseDestinationDirectory(){
        JFileChooser destinationChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        destinationChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        destinationChooser.setAcceptAllFileFilterUsed(false);
        if(destinationChooser.showDialog(null,"Select Directory") == 0){
            destinationDirectory = destinationChooser.getSelectedFile().toString();
        }

        if(destinationDirectory != null) logger.info("chose target directory: " + destinationDirectory);

    }

    public Optional<String> getDestinationFilePath() {
        return Optional.of(destinationDirectory);
    }

    public Map<Path, Path> getFilePathMap() {
        return filePathMap;
    }

    public String getDestinationDirectory() {
        return destinationDirectory;
    }

    public String getSourceFilesDirectory() {
        return sourceFilesDirectory;
    }
}
