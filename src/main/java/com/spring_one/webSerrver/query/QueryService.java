package com.spring_one.webSerrver.query;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QueryService {


    private final QueryRepository queryRepository;

    public List<Query> listQueries() {

        return queryRepository.findAll();

    }


    @Transactional
    public void addNewQuery(Query query) {
        Optional<Query> studentResults = queryRepository.findQueryById(query.getId());

        if (studentResults.isPresent()) {
            throw new IllegalStateException("A record with this email address already exists.");
        }

        queryRepository.save(query);
    }
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
