package com.mantasv.demo.repositories;

import com.mantasv.demo.model.Author;
import com.mantasv.demo.model.Publisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {

    @Query("from Publisher")
    List<Publisher> findAllPublishersJPQL();

}
