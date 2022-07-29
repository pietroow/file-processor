package br.com.home;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static final String PATH = "C:\\Users\\victo\\Documents\\file-processor";

    public static void main(String[] args) {
        FileProcessorHandler.start(PATH);
    }

}
