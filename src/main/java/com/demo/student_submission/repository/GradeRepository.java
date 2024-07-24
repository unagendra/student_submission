package com.demo.student_submission.repository;

import com.demo.student_submission.entity.Grade;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GradeRepository extends CrudRepository<Grade,Long> {
    /**
     * Find Grade based on studentId
     * SB automatically implements interface and Overide  this method and creates query based on method signature.
     * Optional is container around object that risks being NULL
     * @param studentId,courseId
     * @return Optional<Grade>
     */
    Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);

    @Transactional
    void deleteByStudentIdAndCourseId(Long studentId, Long courseId);

    /**
     * Get the List of grades by passing studentID
     * /grade/student/1
     * @param studentId
     * @return List<Grade>
     */
    List<Grade> findByStudentId(Long studentId);

    List<Grade> findByCourseId(Long courseId);

}