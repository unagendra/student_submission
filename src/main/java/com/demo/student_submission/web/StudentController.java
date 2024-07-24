package com.demo.student_submission.web;


import com.demo.student_submission.entity.Course;
import com.demo.student_submission.entity.Student;
import com.demo.student_submission.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {

    StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        return  new ResponseEntity<>(studentService.getStudent(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student){ // deserialize json to java object
        return new ResponseEntity<>(studentService.saveStudent(student),HttpStatus.CREATED); //serialize java object into json
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentService.getStudents(),HttpStatus.OK);

    }

    @GetMapping("/{id}/courses")
    public  ResponseEntity<Set<Course>> getEnrolledCourse(@PathVariable Long id){
        return  new ResponseEntity<>(studentService.getEnrolledCourse(id),HttpStatus.OK);
    }

}
