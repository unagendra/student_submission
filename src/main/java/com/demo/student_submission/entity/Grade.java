package com.demo.student_submission.entity;

import com.demo.student_submission.validation.Score;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="grade",uniqueConstraints = {  //Table level constraints -> Student_id","course_id must be unique
        @UniqueConstraint(columnNames = {"Student_id","course_id"})
})
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //autogenerate the ID
    @Column(name = "id")
    private Long id;


    @Score(message = "invalid Score")
    @Column(name = "score", nullable = false)
    private String score;

    //Associations
    @ManyToOne(optional = false)   //Existence of Student is not optional, grade need to assigned to Student
    @JoinColumn(name = "Student_id", referencedColumnName = "id")  //id->pk of Student
    private Student student;  //Foreign key(student_id)->Student table

    @ManyToOne(optional = false)  //Existence of course is not optional, grade need to assigned to course
    @JoinColumn(name = "course_id", referencedColumnName = "id")  //id->pk of course
    private Course course;  //Foreign key(course_id)->course table

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", score='" + score + '\'' +
                ", student=" + student +
                ", course=" + course +
                '}';
    }

    //    public Grade(Long id, String score, Student student, Course course) {
//        this.id = id;
//        this.score = score;
//        this.student = student;
//        this.course = course;
//    }

    //    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }


//    public Course getCourse() {
//        return course;
//    }
//
//    public void setCourse(Course course) {
//        this.course = course;
//    }
}







