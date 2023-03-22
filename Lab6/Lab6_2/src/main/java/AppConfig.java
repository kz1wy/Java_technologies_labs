import module.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    @Scope("singleton")
    public List<Product> productList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Product 1", 15.0, "abcxyz"));
        products.add(new Product(2, "Product 2", 2.0, "1231sadas 2"));
        products.add(new Product(3, "Product 3", 3.0, "qweh1i2rgoasx 3"));
        return products;
    }
}