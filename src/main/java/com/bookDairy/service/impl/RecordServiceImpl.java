package com.bookDairy.service.impl;

import com.bookDairy.domain.Book;
import com.bookDairy.domain.Record;
import com.bookDairy.repository.BookRepository;
import com.bookDairy.repository.RecordRepository;
import com.bookDairy.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService{

    private final RecordRepository recordRepository;
    private final BookRepository bookRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository, BookRepository bookRepository) {
        this.recordRepository = recordRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Record get(Long bookId, Long recordId) {
        Book book = bookRepository.findOne(bookId);
        if (book==null) {throw new RuntimeException("There isn't book with this record.");}

        return recordRepository.findOne(recordId);
    }

    @Override
    public Record save(Long bookId, Record record) {
        Book book = bookRepository.findOne(bookId);
        if (book==null) {throw new RuntimeException("There isn't book.");}

        //save to "record" collection
        record.setBook(book);
        return recordRepository.save(record);
    }

    @Override
    public Record update(Long bookId, Long id, Record record) {

        Book book = bookRepository.findOne(bookId);
        if (book==null) {throw new RuntimeException("There isn't book.");}

        //update in "record" collection
        record.setBook(book);
        return recordRepository.save(record);
    }

    @Override
    public void delete(Long bookId, Long id) {
        Book book = bookRepository.findOne(bookId);
        if (book==null) {throw new RuntimeException("There isn't book.");}

        //delete from "record" collection
        recordRepository.delete(id);

    }

    @Override
    public List<Record> getAll(Long bookId) {
        Book book = bookRepository.findOne(bookId);
        if (book==null) {throw new RuntimeException("There isn't book.");}

        return recordRepository.findAll().stream()
                .filter(record -> record.getBook()
                .getId()==bookId)
                .collect(Collectors.toList());
    }
}
