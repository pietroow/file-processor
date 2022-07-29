package br.com.home.abstracts;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class PdfFileProcessor implements FileProcessor {

    @Override
    public String process(Path path) {
        File file = path.toFile();
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(1);
            stripper.setEndPage(document.getNumberOfPages());
            return stripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
