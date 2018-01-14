package com.bookDairy.service;

import com.bookDairy.domain.Book;
import com.bookDairy.domain.Record;

import java.util.List;

/**
 * Created by Maryna Kontar.
 */
public interface BookService {

    Book get(Long id);
    Book save(Book book);
    Book update(Book book);
    void delete(Long id);
    List<Book> getAll();
    Record saveRecordForBook(Long bookId, Record record);
}
