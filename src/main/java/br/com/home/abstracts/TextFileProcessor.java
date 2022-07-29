package br.com.home.abstracts;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileProcessor implements FileProcessor {

    @Override
    public String process(Path path) {
        try {
            return Files.readString(path.toAbsolutePath(), StandardCharsets.US_ASCII);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
