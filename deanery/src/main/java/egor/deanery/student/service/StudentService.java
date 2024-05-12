package egor.deanery.student.service;

import egor.deanery.student.model.Student;
import egor.deanery.student.repository.StudentRepository;
import egor.deanery.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentService {
    StudentRepository studentRepository;
    TeacherService teacherService;

    public StudentService(StudentRepository studentRepository, TeacherService teacherService) {
        this.studentRepository = studentRepository;
        this.teacherService = teacherService;
    }

    public Student getStudentInfo(Long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        return student;
    }
    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public List<String> viewArrears(Long id){       // просмотр долгов
        return studentRepository.findAllArrearsById(id);
    }

    public void writeStatementToDeanery(String text, Long id){ //написание заявления
        Student s = studentRepository.findById(id).orElseThrow();
        s.setStatement(text);
        studentRepository.saveAndFlush(s);
    }
    public String findStatement(Long id){ // поиск завяления
        return studentRepository.findStatementByStudentId(id);
    }
    /*public void payOffUniversityDept(Long id, String arrear){ // погае
        if (teacherService.approve().equals(true)){
            Student s = studentRepository.findById(id).orElseThrow();
            List<String> studentArrears = s.getArrears();
            studentArrears.remove(arrear);
            s.setArrears(studentArrears);
            studentRepository.save(s);
        } else {
            System.out.println("Нет разрешения от преподавателя");
        }
    }*/
}
