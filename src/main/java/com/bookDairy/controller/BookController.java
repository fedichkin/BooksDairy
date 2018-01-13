package com.bookDairy.controller;

import com.bookDairy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Maryna Kontar.
 */
@RestController
@RequestMapping("/book")
public class BookController {
//    private BookService bookService;
//
//    @Autowired
//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

    @GetMapping("/books")
    public String books(){
        return "There are must be list of your books.";
    }

}
