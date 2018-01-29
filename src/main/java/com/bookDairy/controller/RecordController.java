package com.bookDairy.controller;

import com.bookDairy.domain.Record;
import com.bookDairy.service.RecordService;
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
@RequestMapping("/books/{book_id}/records")
public class RecordController {

    private RecordService recordService;
    private Map<String, Object> headers;
//    private HttpHeaders httpHeaders ;


    public RecordController(RecordService recordService, Map<String, Object> headers
//            , HttpHeaders httpHeaders
    ) {
        this.recordService = recordService;
        this.headers = headers;
        headers.put("success", true);
//        this.httpHeaders = httpHeaders;
//        httpHeaders.add("success", "true");
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Record>> records(@PathVariable("book_id") Long book_id){
        return new ResponseEntity<>(recordService.getAll(book_id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Record> save(@PathVariable("book_id") Long book_id, @RequestBody Record record){
        return new ResponseEntity<>(recordService.save(book_id, record), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Record> get(@PathVariable("book_id") Long book_id, @PathVariable("id") Long id) {
        return new ResponseEntity<>(recordService.get(book_id, id), HttpStatus.OK);
    }

    @PostMapping("/{id}/remove")
    public ResponseEntity<HttpStatus> remove(@PathVariable("book_id") Long book_id, @PathVariable Long id) {
        recordService.delete(book_id, id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/{id}/edit")
    public ResponseEntity<Record> update(@PathVariable("book_id") Long book_id, @PathVariable Long id,
                                         @RequestBody Record record) {
        return new ResponseEntity<>(recordService.update(book_id, id, record), HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleException(RuntimeException ex, HttpServletRequest request) {
        //TODO 1 Ярик, посмотри, как тебе лучше получать headers. Через Map<String, Object> headers или  HttpHeaders httpHeaders?
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("success", "false");
//        httpHeaders.add("messageError", "Something wrong: " + ex.getMessage());
//        return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);

        Map<String, Object> headers = new HashMap<>();
        headers.put("success", false);
        headers.put("messageError", "Something wrong: " + ex.getMessage());
        //TODO 2 Ярик, посмотри, что еще надо в headers вернуть
//        headers.put("status", HttpStatus.BAD_REQUEST.value());
//        headers.put("error", ex.getMessage());
//        headers.put("exception", ex.getClass().getName());
//        headers.put("path", request.getRequestURI());
        return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);

    }
}
