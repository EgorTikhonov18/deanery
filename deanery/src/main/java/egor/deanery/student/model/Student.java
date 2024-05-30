package egor.deanery.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "student")
@Table(name = "student")
@Data
@Builder
@Schema(name = "Студент")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "student_id")
    private Long studentId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "groupNumber")
    private String groupNumber;
    @Column(name = "departmentName")
    private String departmentName; //кафедра
    @Column(name = "directionOfStudy")
    private String directionOfStudy; //напрвление подготовки
    @Column(name = "arrears")
    private List<String> arrears; //задолжности
    @Column(name = "statement")
    private String statement; //заявление

}
