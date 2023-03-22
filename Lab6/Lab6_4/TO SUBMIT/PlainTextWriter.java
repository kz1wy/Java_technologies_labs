package module;
import org.springframework.stereotype.Component;
import repository.TextWriter;

import java.io.FileWriter;
import java.io.IOException;
@Component
public class PlainTextWriter  implements TextWriter {

    @Override
    public void write(String fileName, String text) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(text);
        }
    }


}
