package com.spring_one.webSerrver.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

// RestController annotation indicates this class serves as a controller where methods are responsible for handling incoming HTTP requests and generating responses.
@RestController
// The HTTP request would be routed to the provided path "api/v1/students". If no matching path is found, Spring will come back with 404.
@RequestMapping(path="api/v1/students",produces = "application/json")
public class StudentController {

    private final StudentService studentService;

    // Autowired label means that this constructor needs to be injected with a StudentService object (a dependency in a safe manner).
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Annotation is used to map GET requests sent to the api/v1/students path to the getStudents() method
    @GetMapping
    public List<Student> getStudents() {

        return studentService.getStudents();

    }

    // Annotation is used to map POST requests sent to the api/v1/students path to the getStudents() method
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
        }

    @PutMapping
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
    }
}
