import java.util.List;

public class Lab1_3 {
    public static void main(String[] args) {
        
        List<Student> list = StudentUtils.generate();
        StudentUtils.print(list);

        StudentUtils.sortByName(list);
        StudentUtils.print(list);

        StudentUtils.sortByAvg(list);
        StudentUtils.print(list);

     
        StudentUtils.sortByAgeDescending(list);
        StudentUtils.print(list);

        double avg = StudentUtils.avg(list);
        System.out.println(avg);


        System.out.println(StudentUtils.goodStudentName(list));

    }
}
