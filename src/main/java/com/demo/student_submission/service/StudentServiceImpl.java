package com.demo.student_submission.service;


import com.demo.student_submission.entity.Course;
import com.demo.student_submission.entity.Grade;
import com.demo.student_submission.entity.Student;
import com.demo.student_submission.exception.StudentNotFoundException;
import com.demo.student_submission.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {


    StudentRepository studentRepository; //spring container(CRUD Repository Interface)

    @Override
    public Student getStudent(Long id) {
        //printGrades(studentRepository.findById(id).get());
        //retrieve student based on id
        Optional<Student> student= studentRepository.findById(id);
//        if (student.isPresent()){
//            return student.get();
//        } else {
//            throw  new StudentNotFoundException(id);
//        }
       return unwrapStudent(student,id);
    }

    @Override
    public Student saveStudent(Student student) { //add student to Database
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) { //delete student based on id
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudents() { //retrieve all students
        return (List<Student>) studentRepository.findAll();  //type cast iterable to List<Student>
    }

    static Student unwrapStudent(Optional<Student> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new StudentNotFoundException(id);
    }

    @Override
    public Set<Course> getEnrolledCourse(Long id) {
       Student student=getStudent(id);
      return student.getCourses();


    }

    /**
     * Bidirectional relationship
     * Here we are able to access all the grade from Student entity(parent side)
     * @param student
     */
//    void printGrades(Student student){
//        for (Grade grade:student.getGrades()){
//            System.out.println(grade.getScore());
//        }
//    }

}