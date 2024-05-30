package egor.deanery.user.controller;

import egor.deanery.student.model.Student;
import egor.deanery.student.repository.StudentRepository;
import egor.deanery.teacher.model.Teacher;
import egor.deanery.teacher.repository.TeacherRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Tag(name = "Контроллер администратора", description = "Управления данными системы")
@RequiredArgsConstructor
public class AdminController {
    private final StudentRepository studentRepository;
    private final TeacherRepository tutorRepository;
    @Operation(
            summary = "Добавление студента",
            description = "Позволяет добавить сущность 'студент' в базу данных"
    )
    @SecurityRequirement(name = "JWT")

    @PostMapping("/add-student")
    public ResponseEntity<?> addStudent(@RequestBody Student studentDto){
        if (studentRepository.existsById(studentDto.getStudentId())){
            return ResponseEntity.badRequest().body("Студент уже существует");
        }
        else{
            var student = Student.builder()
                    .studentId(studentDto.getStudentId())
                    .firstName(studentDto.getFirstName())
                    .lastName(studentDto.getLastName())
                    .groupNumber(studentDto.getGroupNumber())
                    .departmentName(studentDto.getDepartmentName())
                    .directionOfStudy(studentDto.getDirectionOfStudy())
                    .arrears(studentDto.getArrears())
                    .statement(studentDto.getStatement())
                    .build();
            studentRepository.save(student);
            return ResponseEntity.ok(student);
        }
    }
    @Operation(
            summary = "Добавление преподавателя",
            description = "Позволяет добавить сущность 'преподаватель' в базу данных"
    )
    @SecurityRequirement(name = "JWT")

    @PostMapping("/add-tutor")
    public ResponseEntity<?> addTutor(@RequestBody Teacher tutorDto){
        if (tutorRepository.existsById(tutorDto.getTeacherId())){
            return ResponseEntity.badRequest().body("Преподаватель уже существует");
        }
        else{
            var tutor = Teacher.builder()
                    .teacherId(tutorDto.getTeacherId())
                    .firstName(tutorDto.getFirstName())
                    .lastName(tutorDto.getLastName())
                    .department(tutorDto.getDepartment())
                    .scienceDegree(tutorDto.getScienceDegree())
                    .qualificationCategory(tutorDto.getQualificationCategory())
                    .statement(tutorDto.getStatement())
                    .build();
            tutorRepository.save(tutor);
            return ResponseEntity.ok(tutor);
        }
    }
}
