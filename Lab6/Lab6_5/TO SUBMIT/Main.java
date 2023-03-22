import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DatabaseConfig.class);
        context.refresh();
        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println("Username: " + dataSource.getUsername());
    }
}