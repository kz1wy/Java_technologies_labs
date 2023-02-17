import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {
        int choice = 1;
        do{
            System.out.println("1. Read product list");
            System.out.println("2. Read a product by input id");
            System.out.println("3. Add a new product, the result is the product id (auto increment)");
            System.out.println("4. Update a product");
            System.out.println("5. Delete a product");
            System.out.println("6. Exit");
            Scanner sc = new Scanner(System.in);
            System.out.print("Your choice: ");
            choice = sc.nextInt();
            int id = 0;
            String name;
            double price;
            Product product;
            switch(choice){
                case 1:
                    getAllProduct();
                    break;
                case 2:
                    System.out.print("input the id to find: ");
                    id = sc.nextInt();
                    read(id);
                    break;
                case 3:
                    System.out.print("input product name: ");
                    name = sc.next();
                    System.out.print("input product price: ");
                    price = sc.nextDouble();
                    product = new Product(name, price);
                    addProduct(product);
                    break;
                case 4:
                    System.out.print("id of product: ");
                    id = sc.nextInt();
                    System.out.print("input product name: ");
                    name = sc.next();
                    System.out.print("input product price: ");
                    price = sc.nextDouble();
                    product = new Product(id, name, price);
                    updateProduct(product);
                    break;
                case 5:
                    System.out.print("input product id to delete: ");
                    id = sc.nextInt();
                    deleteProduct(id);
                    break;
                default:
                    System.exit(0);
            }
        }while (choice != 6);
    }
    public static void addProduct(Product product) throws SQLException {
        ProductDAO.getInstance().add(product);
    }
    public static void updateProduct(Product product){
        ProductDAO.getInstance().update(product);
    }
    public static void deleteProduct(int id) throws SQLException {
        ProductDAO.getInstance().delete(id);
    }
    public static void getAllProduct(){
        List<Product> list;
        list = ProductDAO.getInstance().readAll();
        for(Product product : list)
            System.out.println(product.toString());
    }
    public static void read(int id){
        Product product = ProductDAO.getInstance().read(id);
        System.out.println(product.toString());
    }
}
