package filehandler;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
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
public class FileHandler {
    private String destinationDirectory;
    private String sourceFilesDirectory;
    private Set<Path> sourceFiles;
    private static final Logger logger = LogManager.getLogger(FileHandler.class);

    /**
     * Method that gets the source files from chosen directory and nested directories if required
     * @param nestedFoldersPattern
     */
    public void fillSourceFiles(String nestedFoldersPattern) throws IOException {
        //TODO jak ma działać maska
        sourceFiles = new HashSet<>();
        try(DirectoryStream<Path> sourceFilesStream = Files.newDirectoryStream(Path.of(sourceFilesDirectory))){
            for (Path path : sourceFilesStream){
                if(path.toFile().isFile()) sourceFiles.add(path);
                //TODO maska, directories, wyrzucić skróty itd...
            }
        }
    }


    public void chooseSourceFilesDirectory(){
        JFileChooser sourceChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        sourceChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        sourceChooser.setAcceptAllFileFilterUsed(false);
        if(sourceChooser.showDialog(null,"Select Directory") == 0){
            sourceFilesDirectory = sourceChooser.getSelectedFile().toString();
        }

        if(sourceFilesDirectory != null) logger.info("chose source directory: " + sourceFilesDirectory);

    }

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

    public Set<Path> getSourceFiles() {
        return sourceFiles;
    }
}
