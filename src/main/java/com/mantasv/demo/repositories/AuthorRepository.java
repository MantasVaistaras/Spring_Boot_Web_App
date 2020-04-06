package com.mantasv.demo.repositories;

import com.mantasv.demo.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Query("from Author")
    List<Author> findAllAuthorsJPQL();

    @Query("from Author a where a.lastName='Evans'")
    List<Author> findEvansJPQL();

    @Query("from Author a where a.lastName='Evans' OR a.firstName='Rod'")
    List<Author> findEvansAndRodJPQL();

}