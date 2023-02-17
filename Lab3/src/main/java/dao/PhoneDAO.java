//import org.hibernate.Session;
//
//public class PhoneDAO {
//
//    public static void main(String[] args) {
//
//        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
//            // Begin a unit of work
//            session.beginTransaction();
//
//            // Insert phone
//
//            Phone phone1 = new Phone();
//            phone1.setName("Iphone 18 pro max plus extra ultra");
//            phone1.setColor("Green");
//            phone1.setCountry("USA");
//            phone1.setQuantity(100);
//            int id = (int) session.save(phone1);
//            System.out.println("phone id = " + phone1);
//
////            // Count user from database
////            Long numberOfUser = session.createQuery("SELECT COUNT(id) FROM User", Long.class).uniqueResult();
////            System.out.println("Number of user in database: " + numberOfUser);
////
////            // Get user by id
////            User savedUser = session.find(User.class, userId);
////            System.out.println("savedUser: " + savedUser);
////
////            // Update user
////            savedUser.setFullname("GP Coder");
////            session.update(savedUser);
////
////            // Get users
////            List<User> users = session.createQuery("FROM User", User.class).list();
////            users.forEach(System.out::println);
////
////            // Delete user
////            session.delete(savedUser);
////
////            // Count user from database
////            numberOfUser = session.createQuery("SELECT COUNT(id) FROM User", Long.class).uniqueResult();
////            System.out.println("Number of user in database: " + numberOfUser);
//
//            // Commit the current resource transaction, writing any unflushed changes to the database.
//            session.getTransaction().commit();
//        }
//    }
//}