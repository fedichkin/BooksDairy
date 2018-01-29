package com.bookDairy.controller;

import com.bookDairy.domain.Book;
import com.bookDairy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private BookService bookService;
    private Map<String, Object> headers;

    @Autowired
    public BookController(BookService bookService, Map<String, Object> headers) {
        this.bookService = bookService;
        this.headers = headers;
        headers.put("success", true);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Book>> books(){
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody Book book){
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
}

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Book> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookService.get(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/remove")
    public ResponseEntity<HttpStatus> remove(@PathVariable Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/{id}/edit")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.update(book), HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleException(RuntimeException ex, HttpServletRequest request) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("success", false);
        headers.put("messageError", "Something wrong: " + ex.getMessage());
        return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

}
