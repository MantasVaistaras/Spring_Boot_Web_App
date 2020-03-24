package com.mantasv.demo.repositories;

import com.mantasv.demo.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}