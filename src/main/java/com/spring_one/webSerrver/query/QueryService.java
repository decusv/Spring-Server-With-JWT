package com.spring_one.webSerrver.query;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * The QueryService class provides methods for managing Query entities.
 * It serves as a service layer between the controller and the repository.
 */
@Service
@RequiredArgsConstructor
public class QueryService {


    private final QueryRepository queryRepository;

    /**
     * Retrieves a list of all Query entities from the database.
     *
     * @return a list of Query entities
     */
    public List<Query> listQueries() {

        return queryRepository.findAll();

    }

    /**
     * Adds a new Query entity to the database.
     *
     * @param query the Query entity to be added
     * @throws IllegalStateException if a Query entity with the same ID already exists
     */
    @Transactional
    public void addNewQuery(Query query) {
        Optional<Query> studentResults = queryRepository.findQueryById(query.getId());

        if (studentResults.isPresent()) {
            throw new IllegalStateException("A record with this email address already exists.");
        }

        queryRepository.save(query);
    }

    /**
     * Deletes a Query entity from the database based on its ID.
     *
     * @param id the ID of the Query entity to be deleted
     * @throws IllegalStateException if a Query entity with the specified ID does not exist
     */
    @Transactional
    public void deleteQuery(Long id) {
        boolean exists = queryRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "Student with id " + id + " does not exist"
            );
        }
        queryRepository.deleteById(id);
    }

    /**
     * Updates an existing Query entity in the database.
     *
     * @param query the Query entity to be updated
     * @throws IllegalStateException if a Query entity with the specified ID does not exist
     */
    @Transactional
    public void updateQuery(Query query) {

        Optional<Query> results = queryRepository.findQueryById(query.getId());

        if (results.isPresent()) {
            Query existingQuery = results.get();
            existingQuery.setLlmSystemName(query.getLlmSystemName()); // Update the name field

            // Save the updated student back to the database
            queryRepository.save(existingQuery);
        } else {
            throw new IllegalStateException("Student with this email doesn't exist");
        }



    }
}
