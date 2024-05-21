package com.spring_one.webSerrver.security.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// We label this as @Repository as this interface is responsible Data Access.
@Repository
/*
    We extend a JpaRepository interface. The first entity tape in the interface specifies the object that the repository will manage. Long refers to the type of the ID field for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    // Uses JPQL (Java Persistence Query Language).
    // The annotation means JPA automatically implements this method using the provided query.
    @Query("SELECT x FROM User x WHERE x.email = ?1")
    Optional<User> findUserByEmail(String email);
}
