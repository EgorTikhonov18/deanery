package egor.deanery.teacher.controller;

import egor.deanery.student.model.Student;
import egor.deanery.teacher.model.Teacher;
import egor.deanery.teacher.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/teachers")
//@PostAuthorize("@authService.hasRole(authentication, 'TEACHER') && request.path.startsWith('/teachers')")
@PreAuthorize("hasRole('TEACHER')")
public class TeacherController {
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    TeacherService teacherService;



    @GetMapping("/info")
    public @ResponseBody Teacher getTeacherInfo(@RequestParam Long id){
       return teacherService.getTeacherInfo(id);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/teacher")
    public Teacher saveTeacher(@RequestBody Teacher teacher){ //POST PostMapping("/teacher")
        return teacherService.saveTeacher(teacher);
    }

    @GetMapping("/teacher/student/{id}/arrears")
    public List<String> viewArrears(@RequestParam Long id){
        return teacherService.viewArrears(id);
    }
    @GetMapping("/teacher/{id}/statement") //@PostMapping("/teacher/{id}/statement")
    public void writeStatementToDeanery(@RequestBody String text, @PathVariable Long id){
        teacherService.writeStatementToDeanery(text, id);
    }
    @GetMapping("/teacherId/{id}/statement")
    public String getStatement( @RequestParam Long id){
        return teacherService.findStatement(id);
    }
    @GetMapping("/teacher/student/{id}/arrear")            //создание задолжности POST
    public void createArrear( @PathVariable Long id, @RequestBody String arrear){
         teacherService.createArrear(id, arrear);
    }
    @GetMapping("/teacher/student/{id}/delArrear") //DELETE
    public void deleteArrear( @PathVariable Long id, @RequestBody String arrear){
        teacherService.deleteArrear(id, arrear);
    }
//   /* @PostMapping("/student/{id}/approve")
//    public Boolean approve(@PathVariable Long id,@RequestBody Boolean answer){
//        return teacherService.approve(id, answer);
//    }*/
}
