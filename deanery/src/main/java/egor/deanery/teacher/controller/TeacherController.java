package egor.deanery.teacher.controller;

import egor.deanery.student.model.Student;
import egor.deanery.teacher.model.Teacher;
import egor.deanery.teacher.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/teachers")
@Tag(name = "Контроллер преподавателей",description = "Возможность получения и внесения определенной информации")
//@PreAuthorize("hasRole('TEACHER')")
public class TeacherController {
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    TeacherService teacherService;

    @GetMapping("/info")
    @SecurityRequirement(name = "JWT")

    public @ResponseBody Teacher getTeacherInfo(@RequestParam Long id){
       return teacherService.getTeacherInfo(id);
    }

   /* //@PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/teacher")
    @SecurityRequirement(name = "JWT")

    public Teacher saveTeacher(@RequestBody Teacher teacher){ //POST PostMapping("/teacher")
        return teacherService.saveTeacher(teacher);
    }*/

    @GetMapping("/teacher/student/{id}/arrears")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Посмотреть задолженности студента",
            description = "Позволяет посмотреть задолженности студента"
    )
    public List<String> viewArrears(@RequestParam Long id){
        return teacherService.viewArrears(id);
    }

    @PostMapping("/teacher/{id}/statement")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Написать заявление",
            description = "Написать заявление в деканат"
    )
    public void writeStatementToDeanery(@PathVariable Long id, @RequestBody String text){
        teacherService.writeStatementToDeanery(text, id);
    }
    @GetMapping("/teacherId/{id}/statement")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Просмотреть заявление",
            description = "Позволяет просмотреть заявление по id преподавателя"
    )
    public String getStatement( @RequestParam Long id){
        return teacherService.findStatement(id);
    }

    @PostMapping("/teacher/student/{id}/arrear")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Создать задолженность студенту",
            description = "Позволяет Создать задолженность студенту по его id"
    )
    public void createArrear( @PathVariable Long id, @RequestBody String arrear){
         teacherService.createArrear(id, arrear);
    }
    @DeleteMapping("/teacher/student/{id}/delArrear")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Удалить задолженность студенту",
            description = "Позволяет погасить задолженность студенту по его id"
    )
    public void deleteArrear( @PathVariable Long id, @RequestBody String arrear){
        teacherService.deleteArrear(id, arrear);
    }
}
