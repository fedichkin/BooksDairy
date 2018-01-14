package com.bookDairy.controller;

import com.bookDairy.domain.Book;
import com.bookDairy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Maryna Kontar.
 */
@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Book>> books(){
        List<Book> books = bookService.getAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody Book book){
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
}

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Book> getCustomer(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<HttpStatus> remove(@PathVariable Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.update(book), HttpStatus.OK);
    }

}
