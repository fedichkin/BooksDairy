package com.bookDairy.service.impl;

import com.bookDairy.domain.Book;
import com.bookDairy.domain.Record;
import com.bookDairy.repository.BookRepository;
import com.bookDairy.repository.RecordRepository;
import com.bookDairy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Maryna Kontar.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private final BookRepository bookRepository;
    private final RecordRepository recordRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, RecordRepository recordRepository) {
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
        //TODO delete all records for book
        bookRepository.delete(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Record saveRecordForBook(Long bookId, Record record) {
        Book book = bookRepository.findOne(bookId);
        record.setBook(book);
        record = recordRepository.save(record);
        List<Record> records = book.getRecordList();
        if(records == null){records = new ArrayList<>(); }
        records.add(record);
        book.setRecordList(records);
        bookRepository.save(book);
        return null;
    }

}
