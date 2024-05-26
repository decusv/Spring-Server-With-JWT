package com.spring_one.webSerrver.query;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @SuppressWarnings("unused")
    public List<Query> listQueries() {

        return queryService.listQueries();

    }

    /**
     * Annotation is used to map POST requests sent to the api/v1/queries path
     * to the addQuery() method.
     */
    @PostMapping
    @Operation(summary = "generate a query", description = "Generates a query with a text input.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A query has been successfully generated.",
                    content = @Content(schema = @Schema(implementation = Query.class))),
    })
    @SuppressWarnings("unused")
    public void addQuery(@RequestBody Query query) {
        queryService.addNewQuery(query);
    }

    /**
     * Annotation is used to map DELETE requests sent to the api/v1/queries/{queryId} path
     * to the deleteQuery() method.
     */
    @DeleteMapping(path = "{queryId}")
    @Operation(summary = "Delete a query", description = "Delete a query with provided query identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A query has been successfully deleted.",
                    content = @Content(schema = @Schema(implementation = Query.class))),
    })
    @SuppressWarnings("unused")
    public void deleteQuery(@PathVariable("queryId") Long id) {
        queryService.deleteQuery(id);
        }

    /**
     * Annotation is used to map PUT requests sent to the api/v1/queries path
     * to the updateQuery() method.
     */
    @PutMapping
    @Operation(summary = "Update a query", description = "Updates a query.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A query has been successfully updated.",
                    content = @Content(schema = @Schema(implementation = Query.class))),
    })
    @SuppressWarnings("unused")
    public void updateQuery(@RequestBody Query query) {
        queryService.updateQuery(query);
    }
}
