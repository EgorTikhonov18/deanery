package egor.deanery.teacher.repository;

import egor.deanery.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query(value = "select t.statement from teacher t where id = :id")
    String findStatementByTeacherId(Long id);
    /*@Query(value = "update teacher set statement = :text where statement is null or is not null")
    void updateStatement(String text);*/
    /*@Query(value = "select t from teacher t where id = :id")
    Optional<Teacher> findByTeacherId(String id);*/
}
