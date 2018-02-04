package com.bookDairy.controller;

import com.bookDairy.domain.Book;
import com.bookDairy.domain.Record;
import com.bookDairy.domain.User;
import com.bookDairy.service.BookService;
import com.bookDairy.service.RecordService;
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
    private final RecordService recordService;

    @Autowired
    public HomeController(UserService userService, BookService bookService, RecordService recordService) {
        this.userService = userService;
        this.bookService = bookService;
        this.recordService = recordService;
    }

    @GetMapping("/api/")
    public String sayHello(){
        return "Hello, Junior Java Free Start!";
    }

    @PostConstruct
    public void createInitEntities() {
        User user = new User();
        user.setUsername("Maryna");
        user.setPassword("password");
        userService.save(user);

        Book book = new Book();
//        book.setId(1L);
        book.setUser(user);
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book = bookService.save(book);

        Record record = new Record();
//        record.setId(1L);
        record.setTitle("Builder");
        record.setDescription("Use the Builder template " +
                "when you have to deal with a large number of constructor parameters");

        recordService.save(book.getId(), record);

        Record record1 = new Record();
//        record1.setId(2L);
        record1.setTitle("Builder1");
        record1.setDescription("1111Use the Builder template " +
                "when you have to deal with a large number of constructor parameters");

        recordService.save(book.getId(), record1);
    }

}
