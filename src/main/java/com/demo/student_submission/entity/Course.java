package com.demo.student_submission.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Subject cannot be blank")
    @NonNull
    @Column(name = "subject",nullable = false)
    private String subject;

    @NotBlank(message = "Course code cannot be blank")
    @NonNull
    @Column(name = "code",nullable = false, unique = true)
    private String code;

    @NotBlank(message = "Description cannot be blank")
    @NonNull
    @Column(name = "description",nullable = false)
    private String description;

    @JsonIgnore        //course entity is serialized we do not want this object, recursive loop and request is aborted
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Grade> grades;  //one course is associated with many grades

    //association
    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name="course_student",
        joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
        inverseJoinColumns =  @JoinColumn(name = "student_id", referencedColumnName = "id")
    )
    private Set<Student> students;



}

//    public Long getId() {
//        return this.id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getSubject() {
//        return this.subject;
//    }
//
//    public void setSubject(String subject) {
//        this.subject = subject;
//    }
//
//    public String getCode() {
//        return this.code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getDescription() {
//        return this.description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }


