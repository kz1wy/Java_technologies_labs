import module.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        List<Product> products = context.getBean("productList", List.class);

        System.out.println("list of products: ");
        for (Product product: products){
            System.out.println(product.toString());
        }
    }
}