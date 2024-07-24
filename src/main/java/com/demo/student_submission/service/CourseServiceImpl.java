package com.demo.student_submission.service;


import com.demo.student_submission.entity.Course;
import com.demo.student_submission.entity.Student;
import com.demo.student_submission.exception.CourseNotFoundException;
import com.demo.student_submission.repository.CourseRepository;
import com.demo.student_submission.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    StudentRepository studentRepository;

    @Override
    public Course getCourse(Long id) {
        Optional<Course> course= courseRepository.findById(id);
        return unwrapCourse(course,id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    static Course unwrapCourse(Optional<Course> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new CourseNotFoundException(id);
    }

    @Override
    public Course addStudentToCourse(Long courseId, Long studentId) {
        //instead calling findByID use existing method getCourse(courseID)
        Course course=getCourse(courseId);
        //Every course, associated with  list<Student> so find the student from repo based on ID
        Optional<Student> student=studentRepository.findById(studentId);
        //unwrap the student-> you can use unwrap method in Student ServiceImpl
        Student unwrappedStudent=StudentServiceImpl.unwrapStudent(student,studentId);
        //add unwrapped student into the List<student>
        course.getStudents().add(unwrappedStudent);
        return courseRepository.save(course);
    }

    @Override
    public Set<Student> getEnrolledStudents(Long id) {
        Course course = getCourse(id);
        return course.getStudents(); //list of Students as per course
    }

}
