package com.demo.student_submission.exception;

public class CourseNotFoundException extends RuntimeException { 

    public CourseNotFoundException(Long id) {
        super("The course id '" + id + "' does not exist in our records");
    }
    
}