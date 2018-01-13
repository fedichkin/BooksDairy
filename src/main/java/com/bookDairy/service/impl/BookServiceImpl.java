package com.bookDairy.service.impl;

import com.bookDairy.domain.Book;
import com.bookDairy.repository.BookRepository;
import com.bookDairy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Maryna Kontar.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Book saveBook(Book book) {
        return null;
    }

    @Override
    public Book updateBook(Book book) {
        return null;
    }

    @Override
    public Book deleteBook(Book book) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

}
