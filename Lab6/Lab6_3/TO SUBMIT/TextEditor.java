package module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import repository.TextWriter;

import java.io.IOException;

public class TextEditor {
    private TextWriter writer;
    private String content;

    public TextEditor() {}

    public TextEditor(@Qualifier("plainTextWriter") TextWriter writer) {
        this.writer = writer;
    }

    @Autowired
    public void setWriter(@Qualifier("pdfTextWriter") TextWriter writer) {
        this.writer = writer;
    }

    public void input(String content) {
        this.content = content;
    }

    public void save(String fileName) throws IOException {
        writer.write(fileName, content);
    }

}
