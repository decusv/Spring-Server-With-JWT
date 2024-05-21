package com.spring_one.webSerrver.query;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity // Tells JPA that you want to store this class as an entity in your DB. By default, class name is used as the table.
@Table(name = "students") // This is needed if you want to customize the table name inside your DB.
public class Student {

    // The label @Id is used to annotate how the primary key attribute of the Student entity class will be generated.
    // The @SequenceGenerator label is used to annotate a custom sequence generator used for generating primary key values.
    @Id
    @SequenceGenerator(
            // Defines the name of this custom sequence generator. This attribute is used to uniquely identify the sequence generator inside the @GeneratedValue annotation.
            name = "student_sequence",
            // Specifies the name of the database sequence that the generator is associated with. This is defined inside the DBMS itself i.e., PostgreSQL
            sequenceName = "student_sequence",
            // Specifies the allocation size for fetching values from a sequence.
            allocationSize = 1
    )

    // This annotation is used to specify the strategy used to generate the primary key for entities inside the persistent database.
    @GeneratedValue(
            //
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;

    // The below field doesn't have to be a column inside the database.
    @Transient
    private Integer age;
    private String email;
    private LocalDate dob;

    public Student() {
    }

    public Student(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                '}';
    }
}
