package com.bookDairy.controller;

import com.bookDairy.domain.Book;
import com.bookDairy.domain.Record;
import com.bookDairy.service.BookService;
import com.bookDairy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Created by Maryna Kontar.
 */

@RestController
public class HomeController {
//
    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public HomeController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String sayHello(){
        return "Hello, Junior Java Free Start!";
    }

    @PostConstruct
    public void createInitUser() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book = bookService.save(book);

        Record record = new Record();
        record.setId(1L);
        record.setTitle("Builder");
        record.setDescription("Use the Builder template " +
                "when you have to deal with a large number of constructor parameters");

        bookService.saveRecordForBook(book.getId(), record);
    }

}
