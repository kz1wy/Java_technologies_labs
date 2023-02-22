import java.util.List;
import utils.HibernateUtils;

public class Program {
    public static void main(String[] args) {
        //manufacture must be created first because it has phone's constraint key
        ManufactureDAO m = new ManufactureDAO(HibernateUtils.getSessionFactory());

        Manufacture m1 = new Manufacture("Apple", "USA", 1800000);
        Manufacture m2 = new Manufacture("Samsung", "South Korea", 310000);
        Manufacture m3 = new Manufacture("Xiaomi", "China", 63020);
        Manufacture m4 = new Manufacture("Google", "USA", 400920);

        m.add(m1);
        m.add(m2);
        m.add(m3);
        m.add(m4);

        PhoneDAO p = new PhoneDAO(HibernateUtils.getSessionFactory());

        Phone p1 = new Phone("iPhone 18 pro max extra plus", 50000000, "Blue", "USA", 500);
        Phone p2 = new Phone("Galaxy S23 ultra", 21000000, "Black", "Korea", 1003);
        Phone p3 = new Phone("Galaxy S22 ultra", 19000000, "White", "Korea", 200);
        Phone p4 = new Phone("Galaxy S21 vip", 18990000, "Pink", "Korea", 500);
        Phone p5 = new Phone("Galaxy S20 ultra", 17990000, "Pink", "Korea", 1002);
        Phone p6 = new Phone("Xiaomi Mi 13 Pro", 18500000, "Black", "Korea", 1001);
        Phone p7 = new Phone("Google Pixel 7 pro", 18500000, "Grey", "USA", 1000);

        p.add(p1);
        p.add(p2);
        p.add(p4);
        p.add(p5);
        p.add(p6);
        p.add(p3);

        List<Phone> phones = p.getAll();
        List<Manufacture> manufactures = m.getAll();
        System.out.println("All phones: " + phones);
        System.out.println("All manufacturers: " + manufactures);


        p1.setPrice(28990000);
        p.update(p1);


        p.remove(p2.getId());


        Phone phone = p.get(p1.getId());
        System.out.println("Phone with id " + p1.getId() + ": " + phone);


        Phone highestPricePhone = p.getHighestSellingPrice();
        System.out.println("Phone with highest selling price: " + highestPricePhone);


        List<Phone> sortedPhones = p.getPhoneOrderByCountryName();
        System.out.println("Phones sorted by country and price: " + sortedPhones);


        boolean isPriceAbove50M = p.existPriceAbove50m();
        System.out.println("Is there a phone priced above 50M VND? " + isPriceAbove50M);

        Phone firstPinkPhoneOver15M = p.meetRequirementsPhone();
        System.out.println("First pink phone over 15M VND: " + firstPinkPhoneOver15M);

        long sumEmployees = m.getSumOfEmployees();
        System.out.println("Sum of all employees: " + sumEmployees);

        boolean haveMoreThan100Emp = m.haveMoreThan100Emp();
        System.out.println("> 100 employees?: " + haveMoreThan100Emp);

        Manufacture getLastManufacture = m.getLastManufacture();
        System.out.println("Last manufacture meets the requirements: " + getLastManufacture);

    }
}