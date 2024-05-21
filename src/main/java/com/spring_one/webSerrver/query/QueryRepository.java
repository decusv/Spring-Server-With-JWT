package com.spring_one.webSerrver.query;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;


/**
 * The QueryRepository interface extends the JpaRepository interface provided by Spring Data JPA.
 * It serves as a repository for managing Query entities and provides methods for database operations.
 * The JpaRepository interface takes two generic parameters: the entity type (Query) and the type of the entity's primary key (Long).
 */
@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {

    /**
     * This method retrieves a Query entity from the database based on its ID.
     * It uses a custom JPQL query to fetch the Query entity.
     * The query selects a Query entity (aliased as 'x') from the Query table where the 'id' field matches the provided parameter 'queryId'.
     * The method returns an Optional<Query> to handle cases where the Query entity may or may not be found.
     *
     * @param id the ID of the Query entity to retrieve
     * @return an Optional containing the Query entity if found, or an empty Optional if not found
     */
    @org.springframework.data.jpa.repository.Query("SELECT x FROM Query x WHERE x.id = :queryId")
    Optional<Query> findQueryById(@Param("queryId") Long id);

}