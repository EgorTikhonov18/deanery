package egor.deanery.student.repository;

import egor.deanery.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "select s.arrears from student s where id = :id")
    List<String> findAllArrearsById(Long id);
    @Query(value = "select s.statement from student s where id = :id")
    String findStatementByStudentId(Long id);
}
