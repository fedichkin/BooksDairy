package com.bookDairy.service.impl;

import com.bookDairy.domain.Book;
import com.bookDairy.domain.Record;
import com.bookDairy.repository.BookRepository;
import com.bookDairy.repository.IdCounterRepository;
import com.bookDairy.repository.RecordRepository;
import com.bookDairy.service.BookService;
import com.mongodb.client.model.Sorts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private final IdCounterRepository idCounterRepository;

    @Value("${error.noBookFind}")
    private String noBookFind;

    @Value("${error.noBooksFound}")
    private String noBooksFound;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, RecordRepository recordRepository, IdCounterRepository idCounterRepository) {
        this.bookRepository = bookRepository;
        this.recordRepository = recordRepository;
        this.idCounterRepository = idCounterRepository;
    }

    @Override
    public Book get(Long id) {
        if (!bookRepository.exists(id)) {throw new RuntimeException(noBookFind);}
        return bookRepository.findOne(id);
    }

    //!!! Now the id is auto-generated. If you add the id manually, the document may be overwritten
    @Override
    public Book save(Book book) {
        if (book.getId() == null || !bookRepository.exists(book.getId())){
            book.setId(idCounterRepository.getNextSequence("book"));}
        return bookRepository.save(book);
    }

    //TODO
    @Override
    public Book update(Book book) {
        if (!bookRepository.exists(book.getId())) {throw new RuntimeException(noBookFind);}
    return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        //TODO delete all records for book if architecture of project use separate collections for books and records

        if (!bookRepository.exists(id)) {throw new RuntimeException(noBookFind);}

        //delete all records from book in "record" collection
        recordRepository.findAll()
                .stream()
                .filter(record -> (record.getBook().getId().equals(id)))
                .forEach(record -> recordRepository.delete(record.getId()));

        //delete from "book" collection
//        for (int i = 0; i < ; i++) {
//
//        }

        bookRepository.delete(id);
    }

    @Override
    public List<Book> getAll() {
        if (bookRepository.count()==0) {throw new RuntimeException(noBooksFound);}
        return bookRepository.findAll();
    }

}
