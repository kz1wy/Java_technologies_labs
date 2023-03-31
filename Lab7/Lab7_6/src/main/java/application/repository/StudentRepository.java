package application.repository;

import application.module.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StudentRepository implements PagingAndSortingRepository<Student, Long> {

    private List<Student> students = new ArrayList<>();

    @Override
    public <S extends Student> S save(S entity) {
        students.add(entity);
        return entity;
    }

    @Override
    public <S extends Student> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(this::save);
        return entities;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean existsById(Long id) {
        return students.stream().anyMatch(s -> s.getId().equals(id));
    }

    @Override
    public Iterable<Student> findAll() {
        return students;
    }

    @Override
    public Iterable<Student> findAllById(Iterable<Long> ids) {
        List<Student> result = new ArrayList<>();
        ids.forEach(id -> findById(id).ifPresent(result::add));
        return result;
    }

    @Override
    public long count() {
        return students.size();
    }

    @Override
    public void deleteById(Long id) {
        students.removeIf(s -> s.getId().equals(id));
    }

    @Override
    public void delete(Student entity) {
        students.remove(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Student> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        students.clear();
    }

    @Override
    public Iterable<Student> findAll(Sort sort) {
        List<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort(Comparator.comparing(Student::getAge).reversed()
                .thenComparing(Student::getIeltsScore));
        return sortedStudents;
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Student> sortedStudents = (List<Student>) findAll(Sort.by(Sort.Direction.DESC, "age", "ieltsScore"));
        List<Student> currentPageStudents;

        if (sortedStudents.size() < startItem) {
            currentPageStudents = new ArrayList<>();
        } else {
            int toIndex = Math.min(startItem + pageSize, sortedStudents.size());
            currentPageStudents = sortedStudents.subList(startItem, toIndex);
        }

        return new PageImpl<>(currentPageStudents, PageRequest.of(currentPage, pageSize), sortedStudents.size());
    }

    public List<Student> getStudentsInRange(int fromIndex, int toIndex) {
        return new ArrayList<>(findAll().subList(fromIndex, toIndex));
    }

    @Override
    public Iterable<Student> findAll(Sort sort) {
        return null;
    }
}
