package com.demo.student_submission;

import com.demo.student_submission.exception.CourseNotFoundException;
import com.demo.student_submission.exception.ErrorResponse;
import com.demo.student_submission.exception.GradeNotFoundException;
import com.demo.student_submission.exception.StudentNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    //Exception Handling

    //1.GET Request-ID does not exist

    @ExceptionHandler({CourseNotFoundException.class, GradeNotFoundException.class, StudentNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex){
        ErrorResponse error=new ErrorResponse(Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    //2.An unsuccessful deletion will throw an EmptyResultDataAccessException.

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(EmptyResultDataAccessException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList("Cannot delete non-existing resource"));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //3.Attempting to save the same grade twice throws an DataIntegrityViolationException
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList("Data Integrity Violation: we cannot process your request."));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    //Field Validation : PUT and POST
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        //1. access the errors from the validation process through the binding result:

        List<String> errors = new ArrayList<>(); //store All the errors from Binding result
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            //loop through the error in BR and add Default message to errors array list
            errors.add(error.getDefaultMessage());
        }
        //2.append every error into an ErrorResponse object.
        ErrorResponse errorResponse = new ErrorResponse(errors);

        //3.Return the ErrorResponse with a status code of 400.
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
