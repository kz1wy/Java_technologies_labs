package servlet;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class JSPFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
