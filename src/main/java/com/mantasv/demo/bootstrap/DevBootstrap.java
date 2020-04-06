package com.mantasv.demo.bootstrap;

import com.mantasv.demo.model.Author;
import com.mantasv.demo.model.Book;
import com.mantasv.demo.model.Publisher;
import com.mantasv.demo.repositories.AuthorRepository;
import com.mantasv.demo.repositories.BookRepository;
import com.mantasv.demo.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {

        Publisher p = new Publisher();
        p.setName("foo");
        publisherRepository.save(p);

        //Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", p);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", p);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        //testing @ManyToMany relationship
        eric.getBooks().add(noEJB);
        noEJB.getAuthors().add(eric);
        //update database
        authorRepository.save(eric);
        bookRepository.save(noEJB);

        //Playing with JPQL
        List<Author> myList = authorRepository.findAllAuthorsJPQL();
        display(myList);
        List<Book> myList2 = bookRepository.findAllBooksJPQL();
        display(myList2);
        List<Publisher> myList3 = publisherRepository.findAllPublishersJPQL();
        display(myList3);
        myList = authorRepository.findEvansJPQL();
        display(myList);
        myList = authorRepository.findEvansAndRodJPQL();
        display(myList);
        myList2 = bookRepository.findBooksHavingTheLastDigitInIsbn4();
        display(myList2);

    }

    private <T> void display(List<T> myList) {
        System.out.println("-------------------------------");
        for (T a : myList) {
            System.out.println(a);
        }
        System.out.println("-------------------------------");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
