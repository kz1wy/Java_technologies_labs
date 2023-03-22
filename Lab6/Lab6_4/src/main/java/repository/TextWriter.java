package repository;

import java.io.IOException;

public interface TextWriter {
    void write(String fileName, String text) throws IOException;
}