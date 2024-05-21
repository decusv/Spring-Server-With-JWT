package com.spring_one.webSerrver.query;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a query entity to be stored in the database.
 * This class is mapped to the "queries" table in the database.
 * It includes attributes like id, llmSystemName, llmModelName, querySize, and responseSize.
 * The primary key is generated using a sequence generator.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Indicates that this class is an entity and is mapped to a database table.
@Table(name = "queries") // Indicates the name of the database table to be used for mapping.
public class Query {

    /*
     * The label @Id is used to annotate how the primary key attribute of the Student entity class will be generated.
     * The @SequenceGenerator label is used to annotate a custom sequence generator used for generating primary key values.
     * Defines the name of this custom sequence generator.
     * The "name" attribute is used to uniquely identify the sequence generator inside the @GeneratedValue annotation.
     * The "sequenceName" attribute of the database sequence that the generator is associated with. This is defined inside the DBMS itself i.e., PostgresQL.
     * The "allocationSize" attribute specifies the number of sequence generated values needed before requesting the database to generate another.
     *  - Higher allocation size would be useful when expecting bulk imports.
     *  - Setting size as 50 means that the server will request and hold in-memory all 50 values until all have been assigned and used before calling DB for more.
     *  - Using allocation size of more than 1 can lead to gaps in primary keys if the application crashes.
     */
    @Id
    @SequenceGenerator(
            name = "query_sequence",
            sequenceName = "query_sequence",
            allocationSize = 1
    )

    /*
      Specifies the primary key generation strategy for the entity defined in the class i.e., 'Query'.
      The attribute "generator" specifies the defined DB sequence i.e., "name" from the "SequenceGenerator" annotation.
      The attribute "strategy" defines generation sequence. In this case, incremental.
      - Semantics of the strategy differ between database types.
     */
    @GeneratedValue(
            //
            strategy = GenerationType.SEQUENCE,
            generator = "query_sequence"
    )

    private Long id;

    /**
     * The name of the LLM (Language Learning Model) system.
     */
    private String llmSystemName;

    /**
     * The name of the LLM model.
     */
    private String llmModelName;

    /**
     * The size of the query.
     */
    private Integer querySize;

    /**
     * The size of the response.
     */
    private Integer responseSize;

}
