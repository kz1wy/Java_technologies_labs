package module;
import repository.TextWriter;

import java.io.FileWriter;
import java.io.IOException;

public class PlainTextWriter  implements TextWriter {

    @Override
    public void write(String fileName, String text) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(text);
        }
    }


}
