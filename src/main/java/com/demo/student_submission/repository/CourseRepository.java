package com.demo.student_submission.repository;

import com.demo.student_submission.entity.Course;
import com.demo.student_submission.entity.Grade;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,Long> {
//    /**
//     * Find Grade based on studentId
//     * SB automatically implements interface and Overide  this method and creates query based on method signature
//     * @param id
//     * @return Grade
//     */
//    Course findByCourseId(Long id);
}