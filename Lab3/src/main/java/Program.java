import org.hibernate.Session;

public class Program {

    public static void main(String[] args) {

        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();

            // Insert phone

            Phone phone1 = new Phone();
            phone1.setName("Iphone 18 pro max plus extra ultra");
            phone1.setColor("Green");
            phone1.setCountry("USA");
            phone1.setQuantity(100);
            int id = (int) session.save(phone1);
            System.out.println("phone id = " + phone1);
            session.getTransaction().commit();
        }
    }
}