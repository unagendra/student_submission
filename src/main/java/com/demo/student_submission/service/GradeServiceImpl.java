package com.demo.student_submission.service;


import com.demo.student_submission.entity.Course;
import com.demo.student_submission.entity.Grade;
import com.demo.student_submission.entity.Student;
import com.demo.student_submission.exception.GradeNotFoundException;
import com.demo.student_submission.exception.StudentNotEnrolledException;
import com.demo.student_submission.repository.CourseRepository;
import com.demo.student_submission.repository.GradeRepository;
import com.demo.student_submission.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {


    GradeRepository gradeRepository;
    //assign the grade to a student before saving it
    StudentRepository  studentRepository;

    CourseRepository courseRepository;
    
    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        Optional<Grade> grade=gradeRepository.findByStudentIdAndCourseId(studentId,courseId);
        return  unwrapGrade(grade,studentId,courseId);

    }

    static Grade unwrapGrade(Optional<Grade> entity, Long studentId, Long courseId) {
        if (entity.isPresent()) return entity.get();
        else throw new GradeNotFoundException(studentId, courseId);
    }


    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        //assign the grade to a student and course before saving it
        Student student = StudentServiceImpl.unwrapStudent(studentRepository.findById(studentId), studentId);//find the student, ID belongs to and retrieve
        Course course = CourseServiceImpl.unwrapCourse(courseRepository.findById(courseId), courseId); //find the course, ID belongs to and retrieve

        //StudentNotEnrolledException(student is not enrolled course->client should not be able to assign the grade)
        if (!student.getCourses().contains(course)) throw  new StudentNotEnrolledException(studentId,courseId);

        //Assign the student and course to grade
        grade.setStudent(student);
        grade.setCourse(course);
        System.out.println(grade);
        System.out.println(grade.getScore());
        return gradeRepository.save(grade);
    }


    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {
        //retrieve existing grade from repository(Data base)
       Optional<Grade> grade=gradeRepository.findByStudentIdAndCourseId(studentId,courseId);
        Grade unwrappedGrade = unwrapGrade(grade, studentId, courseId);
        unwrappedGrade.setScore(score);
        return gradeRepository.save(unwrappedGrade);

//        if (grade.isPresent()){
//          Grade unwrappedGrade= grade.get();
//
//           //update the grade with the score passed from the consumer
//           unwrappedGrade.setScore(score);
//           return gradeRepository.save(unwrappedGrade);
//       } else {
//           throw new GradeNotFoundException(studentId,courseId);
//       }


    }


    @Override
    public void deleteGrade(Long studentId, Long courseId) {
         gradeRepository.deleteByStudentIdAndCourseId(studentId,courseId);
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    @Override
    public List<Grade> getAllGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }

}
