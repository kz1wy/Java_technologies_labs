package application.repository;
import application.module.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByAgeGreaterThanEqual(int age);

    Long countByIeltsScore(double ieltsScore);

    List<Student> findByNameContainingIgnoreCase(String name);

}
