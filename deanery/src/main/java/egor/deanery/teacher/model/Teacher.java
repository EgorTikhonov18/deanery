package egor.deanery.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "teacher")
@Entity(name = "teacher")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Преподаватель")

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "department")
    private String department;
    @Column(name = "science_degree")
    private String scienceDegree;
    @Column(name = "qualification")
    private String qualificationCategory;
    @Column(name = "statement")
    private String statement; //заявление

}
