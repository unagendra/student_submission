package com.demo.student_submission.service;



import com.demo.student_submission.entity.Course;
import com.demo.student_submission.entity.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {
    Student getStudent(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    List<Student> getStudents();
    Set<Course> getEnrolledCourse(Long id);
}