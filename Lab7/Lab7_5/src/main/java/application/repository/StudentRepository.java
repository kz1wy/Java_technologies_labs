package application.repository;
import application.module.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.age > :age")
    List<Student> findByAgeGreaterThanEqual(int age);

    @Query("SELECT s FROM Student s WHERE s.ieltsScore >= :score")
    Long countByIeltsScore(double ieltsScore);

    @Query("SELECT s FROM Student s WHERE s.email LIKE %:name%")
    List<Student> findByNameContainingIgnoreCase(String name);

}