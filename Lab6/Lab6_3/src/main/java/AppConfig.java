import module.PdfTextWriter;
import module.PlainTextWriter;
import module.TextEditor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.TextWriter;

@Configuration
public class AppConfig {

    @Bean
    @Qualifier("plainTextWriter")
    public TextWriter plainTextWriter() {
        return new PlainTextWriter();
    }
    @Bean
    @Qualifier("pdfTextWriter")
    public TextWriter pdfTextWriter() {
        return new PdfTextWriter();
    }

//    @Bean
//    public TextEditor textEditor(@Qualifier("plainTextWriter") TextWriter writer) {
//        return new TextEditor(writer);
//    }
    @Bean
    public TextEditor textEditor(@Qualifier("pdfTextWriter") TextWriter writer) {
        return new TextEditor(writer);
    }

}
