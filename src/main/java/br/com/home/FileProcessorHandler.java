package br.com.home;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class FileProcessorHandler {

    private FileProcessorHandler() {
    }

    public static void start(String pathString) {
        Path path = Paths.get(pathString);

        isDirectoryFolder(path);
        String processedPath = createProcessedFolderIfNotExist(path);
        resolveExistingFiles(path, processedPath);
        initWatcher(path);
    }

    private static void isDirectoryFolder(Path path) {
        try {
            Boolean isFolder = (Boolean) Files.getAttribute(path, "basic:isDirectory", NOFOLLOW_LINKS);
            if (Boolean.FALSE.equals(isFolder)) {
                throw new IllegalArgumentException("Path: " + path + " is not a folder");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static String createProcessedFolderIfNotExist(Path path) {
        String subfolderPath = path.toString().concat("\\processed");
        File theDir = new File(subfolderPath);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        return subfolderPath;
    }

    private static void resolveExistingFiles(Path path, String processedPath) {
        File file = path.toFile();
        File[] files = file.listFiles();
        if (files != null) {
            for (File fileDir : files) {
                moveFile(path, processedPath, fileDir);
            }
        }
    }

    private static void moveFile(Path path, String processedPath, File fileDir) {
        if (!fileDir.isDirectory()) {
            String name = fileDir.getName();
            FileProcessorService.process(path, name);
            File dest = new File(processedPath, name);
            dest.delete();
            fileDir.renameTo(dest);
        }
    }

    private static void initWatcher(Path path) {

        System.out.println("Watching path: " + path);

        FileSystem fs = path.getFileSystem();

        try (WatchService service = fs.newWatchService()) {

            path.register(service, ENTRY_CREATE);

            WatchKey key = null;
            // loop
            do {
                key = service.take();

                WatchEvent.Kind<?> kind = null;

                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    kind = watchEvent.kind();
                    if (ENTRY_CREATE == kind) {
                        executeEntryMethod((WatchEvent<Path>) watchEvent, path);
                    }
                }

            } while (key.reset());

        } catch (IOException | InterruptedException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void executeEntryMethod(WatchEvent<Path> watchEvent, Path path) {
        Path fileName = watchEvent.context();
        System.out.println("\nNew file created: " + fileName);
        FileProcessorService.process(path, fileName.toString());
    }

}
