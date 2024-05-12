package egor.deanery.teacher.service;

import egor.deanery.student.model.Student;
import egor.deanery.student.repository.StudentRepository;
import egor.deanery.teacher.model.Teacher;
import egor.deanery.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Service
public class TeacherService {

    TeacherRepository teacherRepository;
    StudentRepository studentRepository;
    @Autowired
    public TeacherService(TeacherRepository teacherRepository, StudentRepository studentRepository){
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }
   /* public void setPasswordAndRole(Long id, String password, String role){
        Teacher t = teacherRepository.findById(id).orElseThrow();
        t.setPassword(password);
        t.setRole(role);
        teacherRepository.saveAndFlush(t);
    }*/

    public Teacher getTeacherInfo(Long id){
        return teacherRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    public Teacher saveTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }

   /* public Boolean approve(Long id,Boolean answer){ //разрешение на погашение задолжности
        Student student = studentRepository.findById(id).orElseThrow();
        return answer;
    }*/
    public List<String> viewArrears(Long id){       // просмотр долгов
        return studentRepository.findAllArrearsById(id);
    }

    public void writeStatementToDeanery(String text, Long id){ //написание заявления препода
        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        teacher.setStatement(text);
        teacherRepository.saveAndFlush(teacher);
    }
    public String findStatement(Long id){ // поиск завяления
        return teacherRepository.findStatementByTeacherId(id);
    }
    public void createArrear(Long id, String arrear){ // создание задолжности
        Student s = studentRepository.findById(id).orElseThrow();
        //s.setArrears(Collections.singletonList(arrear));
        List<String> studentArrears = s.getArrears();
        studentArrears.add(arrear);
        s.setArrears(studentArrears);
        studentRepository.save(s);
    }
    public void deleteArrear(Long id, String arrear){
        Student s = studentRepository.findById(id).orElseThrow();// удаление задолжности
        List<String> studentArrears = s.getArrears();
        studentArrears.remove(arrear);
        s.setArrears(studentArrears);
        studentRepository.save(s);
    }
}
