import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;
import module.*;
public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TextEditor textEditor = context.getBean(TextEditor.class);

        textEditor.input("hehehehhehehehhehe");
        textEditor.save("test.pdf");
    }
}