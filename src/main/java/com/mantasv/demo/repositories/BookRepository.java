package com.mantasv.demo.repositories;

import com.mantasv.demo.model.Author;
import com.mantasv.demo.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Query("from Book")
    List<Book> findAllBooksJPQL();

    @Query("from Book b where b.isbn LIKE '%4'")
    List<Book> findBooksHavingTheLastDigitInIsbn4();

}