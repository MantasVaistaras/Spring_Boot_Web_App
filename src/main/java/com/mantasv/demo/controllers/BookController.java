package com.mantasv.demo.controllers;

import com.mantasv.demo.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")
    public String getAll(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }
}
