import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSource {

    @Autowired
    private DatabaseConfig config;
    public String getUsername(){
        return config.username;
    }
}
