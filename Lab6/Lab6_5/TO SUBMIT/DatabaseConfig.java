import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@PropertySources({
        @PropertySource("classpath:config.properties")
})
public class DatabaseConfig {

    @Value("${db.username}")
    String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.url}")
    String url;

}
