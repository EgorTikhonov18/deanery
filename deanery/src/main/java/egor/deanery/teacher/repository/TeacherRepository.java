package egor.deanery.teacher.repository;

import egor.deanery.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query(value = "select t.statement from teacher t where id = :id")
    String findStatementByTeacherId(Long id);

}
