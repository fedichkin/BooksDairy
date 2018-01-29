package com.bookDairy.controller;

import com.bookDairy.domain.Book;
import com.bookDairy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Maryna Kontar.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private HttpHeaders httpHeaders;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
        httpHeaders = new HttpHeaders();
        httpHeaders.add("success", "true");
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Book>> books(){
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody Book book){
        return ResponseEntity.ok().headers(httpHeaders).body(bookService.save(book));
//        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
}

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Book> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok().headers(httpHeaders).body(bookService.get(id));
    }

    @PostMapping("/{id}/remove")
    public ResponseEntity<HttpStatus> remove(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok().headers(httpHeaders).build();
//        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/{id}/edit")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok().headers(httpHeaders).body(bookService.update(book));
//        return new ResponseEntity<>(bookService.update(book), HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HttpHeaders> handleException(RuntimeException ex, HttpServletRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("success", "false");
        httpHeaders.add("messageError", "Something wrong: " + ex.getMessage());
        return ResponseEntity.badRequest().headers(httpHeaders).build();
    }

}
