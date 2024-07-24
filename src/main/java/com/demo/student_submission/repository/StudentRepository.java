package com.demo.student_submission.repository;


import com.demo.student_submission.entity.Student;
import org.springframework.data.repository.CrudRepository;

//Repository interacts with relational Database

public interface StudentRepository extends CrudRepository<Student,Long> {
    //Spring Container creates a bean and provides implementation of this interface
    //use CRUD Repository for CRUD operations
}