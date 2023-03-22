package module;

import repository.TextWriter;

public class PdfTextWriter implements TextWriter {
    @Override
    public void write(String fileName, String text) {
        System.out.println("Wrote to PDF " + fileName + ": " + text);
    }
}