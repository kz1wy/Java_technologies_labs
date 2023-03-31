package application;

import application.module.Student;
import application.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = {"com.example.application.repository"})
public class Application implements CommandLineRunner {
	@Autowired
	public StudentRepository studentRepository;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// read the list of students sorted by age and ielts score
		Page<Student> students = studentRepository.findAllSortedByAgeAndIeltsScore(0, 10);

		// print the students on pages 4-5-6
		students = studentRepository.findAll(PageRequest.of(3, 3));
		students.forEach(System.out::println);
	}
}
