package com.spring_one.webSerrver.query;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * RestController annotation indicates this class serves as a controller
 * where methods are responsible for handling incoming HTTP requests and generating responses.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/v1/queries",produces = "application/json")
public class QueryController {

    private final QueryService queryService;

    /**
     * Annotation is used to map GET requests sent to the api/v1/queries path
     * to the listQueries() method.
     */
    @GetMapping
    public List<Query> listQueries() {

        return queryService.listQueries();

    }

    /**
     * Annotation is used to map POST requests sent to the api/v1/queries path
     * to the addQuery() method.
     */
    @PostMapping
    public void addQuery(@RequestBody Query query) {
        queryService.addNewQuery(query);
    }

    /**
     * Annotation is used to map DELETE requests sent to the api/v1/queries/{queryId} path
     * to the deleteQuery() method.
     */
    @DeleteMapping(path = "{queryId}")
    public void deleteQuery(@PathVariable("queryId") Long id) {
        queryService.deleteQuery(id);
        }

    /**
     * Annotation is used to map PUT requests sent to the api/v1/queries path
     * to the updateQuery() method.
     */
    @PutMapping
    public void updateQuery(@RequestBody Query query) {
        queryService.updateQuery(query);
    }
}
