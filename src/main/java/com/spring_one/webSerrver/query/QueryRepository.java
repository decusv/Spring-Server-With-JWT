package com.spring_one.webSerrver.query;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// We label this as @Repository as this interface is responsible Data Access.
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Uses JPQL (Java Persistence Query Language).
    // The annotation means JPA automatically implements this method using the provided query.
    @Query("SELECT x FROM Student x WHERE x.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
