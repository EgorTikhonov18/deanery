package egor.deanery.student.controller;

import egor.deanery.student.service.StudentService;
import egor.deanery.student.model.Student;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Slf4j
//@PostAuthorize("@authService.hasRole(authentication, 'STUDENT') && request.path.startsWith('/students')")
@PreAuthorize("hasRole('STUDENT')")
public class StudentController {
    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/info")
    public @ResponseBody Student getStudentInfo(@RequestParam Long id){
        Student student = studentService.getStudentInfo(id);
        return student;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/student")
    public Student saveStudent(@RequestBody Student student){ // POST

        return studentService.saveStudent(student);
    }

    /*@DeleteMapping("/student/{id}/delArrear")
    public void payOffUniversityDept(@PathVariable Long id, @RequestBody String arrear){
        studentService.payOffUniversityDept(id, arrear);
    }*/
    @GetMapping("/student/arrears")
    public List<String> viewArrears(@RequestParam Long id){
            return studentService.viewArrears(id);
    }
    @GetMapping("/student/{id}/statement")
    public void writeStatementToDeanery(@RequestBody String text, @PathVariable Long id){ //POST student/{id}/statement
         studentService.writeStatementToDeanery(text, id);
    }
    @GetMapping("/studentId/{id}/statement")
    public String getStatement( @RequestParam Long id){
        return studentService.findStatement(id);
    }
}
