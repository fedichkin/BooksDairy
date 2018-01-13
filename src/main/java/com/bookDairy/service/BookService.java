package com.bookDairy.service;

import com.bookDairy.domain.Book;

import java.util.List;

/**
 * Created by Maryna Kontar.
 */
public interface BookService {

    Book getBook(Long id);
    Book saveBook(Book book);
    Book updateBook(Book book);
    Book deleteBook(Book book);
    List<Book> getAllBooks();
}
