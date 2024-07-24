package com.demo.student_submission.service;


import com.demo.student_submission.entity.Course;
import com.demo.student_submission.entity.Student;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Course getCourse(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    List<Course> getCourses();
    Course addStudentToCourse(Long courseId, Long studentId);
    //Get  the set<Student> based on the course id
    Set<Student> getEnrolledStudents(Long id);
}