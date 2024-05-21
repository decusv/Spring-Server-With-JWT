package com.spring_one.webSerrver.query;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {

        return studentRepository.findAll();

    }


    @Transactional
    public void addNewStudent(Student student) {
        Optional<Student> studentResults = studentRepository.findStudentByEmail(student.getEmail());

        if (studentResults.isPresent()) {
            throw new IllegalStateException("A record with this email address already exists.");
        }

        studentRepository.save(student);
    }
    @Transactional
    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "Student with id " + id + " does not exist"
            );
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Student student) {

        Optional<Student> results = studentRepository.findStudentByEmail(student.getEmail());

        if (results.isPresent()) {
            Student existingStudent = results.get();
            existingStudent.setName(student.getName()); // Update the name field
            existingStudent.setDob(student.getDob());

            // Save the updated student back to the database
            studentRepository.save(existingStudent);
        } else {
            throw new IllegalStateException("Student with this email doesn't exist");
        }



    }
}
