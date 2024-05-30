package egor.deanery.student.controller;

import egor.deanery.student.service.StudentService;
import egor.deanery.student.model.Student;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PostAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Slf4j
@Tag(name = "Контроллер студентов", description = "Позволяет студентам взаимодействовать с системой")

//@PreAuthorize("hasRole('STUDENT')")
public class StudentController {
    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/info")
    @SecurityRequirement(name = "JWT")
    public @ResponseBody Student getStudentInfo(@RequestParam Long id){
        Student student = studentService.getStudentInfo(id);
        return student;
    }

   /* //@PreAuthorize("permitAll()")
    @PostMapping("/student")
    @SecurityRequirement(name = "JWT")
    public Student saveStudent(@RequestBody Student student){

        return studentService.saveStudent(student);
    }*/
    @GetMapping("/student/arrears")
    @SecurityRequirement(name = "JWT")
    public List<String> viewArrears(@RequestParam Long id){
            return studentService.viewArrears(id);
    }
    @PostMapping("/student/{id}/statement")
    @SecurityRequirement(name = "JWT")
    public void writeStatementToDeanery(@RequestBody String text, @PathVariable Long id){
         studentService.writeStatementToDeanery(text, id);
    }
    @GetMapping("/studentId/{id}/statement")
    @SecurityRequirement(name = "JWT")
    public String getStatement( @PathVariable Long id){
        return studentService.findStatement(id);
    }
}
