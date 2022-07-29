package br.com.home;

import br.com.home.abstracts.FileProcessor;
import br.com.home.chain.ChainExecutor;
import br.com.home.chain.InformationObject;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileProcessorService {

    private FileProcessorService() {
    }

    public static void process(Path folderDirectoryPath, String newFileName) {
        String file = "\\".concat(newFileName);
        String newPathFile = folderDirectoryPath.toString().concat(file);
        Path newPath = Paths.get(newPathFile);

        String extension = getFileExtension(newPath);
        TypeEnum type = TypeEnum.findByText(extension);
        FileProcessor processor = FileProcessorFactory.getFactory(type);

        if(processor != null) {
            String fileContent = processor.process(newPath);
            InformationObject result = ChainExecutor.execute(fileContent);
            moveProcessedFile();
            System.out.println(result);
        }

    }

    private static void moveProcessedFile() {


    }

    private static String getFileExtension(Path path) {
        File file = path.toFile();
        String fileName = file.toString();
        int index = fileName.lastIndexOf(".");
        if (index > 0) {
            return fileName.substring(index + 1);
        }
        return null;
    }

}
