package br.com.home;

import br.com.home.abstracts.FileProcessor;
import br.com.home.abstracts.PdfFileProcessor;
import br.com.home.abstracts.TextFileProcessor;

import static br.com.home.TypeEnum.PDF;
import static br.com.home.TypeEnum.TXT;

public class FileProcessorFactory {

    private FileProcessorFactory() {
    }

    public static FileProcessor getFactory(TypeEnum type) {
        if (PDF.equals(type)) {
            return new PdfFileProcessor();
        } else if (TXT.equals(type)) {
            return new TextFileProcessor();
        }
        return null;
    }

}
