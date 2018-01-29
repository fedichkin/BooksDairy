package com.bookDairy.service.impl;

import com.bookDairy.domain.Book;
import com.bookDairy.domain.Record;
import com.bookDairy.repository.BookRepository;
import com.bookDairy.repository.RecordRepository;
import com.bookDairy.service.BookService;
import com.mongodb.client.model.Sorts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Maryna Kontar.
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final RecordRepository recordRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, RecordRepository recordRepository
    ) {
        this.bookRepository = bookRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public Book get(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    //TODO
    @Override
    public Book update(Book book) {
//        throw new RuntimeException("The update(Book book) method is not yet written.");
    return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        //TODO delete all records for book if architecture of project use separate collections for books and records

        //delete all records from book in "record" collection
        recordRepository.findAll()
                .stream()
                .filter(record -> (record.getBook().getId().equals(id)))
                .forEach(record -> recordRepository.delete(record.getId()));

        //delete from "book" collection
        bookRepository.delete(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }


}
