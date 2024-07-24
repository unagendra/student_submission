package com.demo.student_submission.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="student")
@Getter
@Setter
@RequiredArgsConstructor    //Constructor will update name and Birth_date
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @Past(message = "The birth date must be in the past")
    @NonNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;


    @JsonIgnore     //we do not want this to serialize to json and send back on get request using the id
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL) //Do not create join table,Relationship is defined in course
    private List<Grade> grades;

    //Mapped By non-owning side of the relationship(students)
//    @JsonIgnore
//    @ManyToMany(mappedBy = "students")
//    private Set<Course> courses;

    //Student is also owing side(Both student course are equally responsible to manage the relationship)
    @JsonIgnore
    @ManyToMany
    @JoinTable( //make reference to Join table
            name="course_student",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    private Set<Course> courses;

}

//    public List<Grade> getGrades() {
//        return grades;
//    }
//
//    public void setGrades(List<Grade> grades) {
//        this.grades = grades;
//    }


//    public Long getId() {
//        return this.id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public LocalDate getBirthDate() {
//        return this.birthDate;
//    }
//
//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }


