import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentUtils {
    
    public static List<Student> generate()
    {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Minh", 29, 8, 7.7, 6.5));
        list.add(new Student("Huong", 26, 7.7, 9, 8.1));
        list.add(new Student("Lan", 31, 9, 9.2, 7.0));
        list.add(new Student("Khoa", 24, 8.5, 9, 7.7));
        list.add(new Student("Duy", 18, 7, 6.9, 6.5));
        list.add(new Student("Tu", 29, 6.5, 7.7, 8.4));
        list.add(new Student("Diem", 22, 8, 8.3, 6.9));
        list.add(new Student("Linh", 21, 5, 6.6, 7.7));
        

        return list;
    }

    public static void print(List<Student> list)
    {
        System.out.println("\n============ BEGIN ============ ");
        list.forEach(System.out::println); // example of Method Reference
        System.out.println("============ END ============ \n");
    }


    public static void sortByName(List<Student> list) {
//        Collections.sort(list, new Comparator<Student>() {  //  <--- thay đổi bằng lambda expression
//            @Override
//            public int compare(Student o1, Student o2) {
//                return o1.name.compareTo(o2.name);
//            }
//        });
        Collections.sort(list, (Student o1, Student o2) -> o1.name.compareTo(o2.name));
    }

    /**
     * @TODO
     * Chuyển đổi cách viết sử dụng new Comparator... sang sử dụng Lambda Expression
     */
    public static void sortByAvg(List<Student> list)
    {
//        Collections.sort(list, new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                if (o1.average() > o2.average()) return 1;
//                if (o1.average() < o2.average()) return -1;
//                return 0;
//            }
//        });
        Collections.sort(list, (Student o1, Student o2) -> {
            if (o1.average() > o2.average()) return 1;
            if (o1.average() < o2.average()) return -1;
            return 0;
        });
    }


    public static void sortByAgeDescending(List<Student> list)
    {
        Collections.sort(list, (Student o1, Student o2) -> Integer.compare(o2.age, o1.age));
    }


    public static double avg(List<Student> list)
    {
        return list.stream()
                .mapToDouble(Student::average)
                .average()
                .orElse(0.0);
    }


    public static List<String> goodStudentName(List<Student> list)
    {
        return list.stream()
                .filter(Student::isGoodStudent)
                .map(s -> s.name)
                .collect(Collectors.toList());
    } 
}
