package com.bookDairy.service.impl;

import com.bookDairy.domain.Book;
import com.bookDairy.domain.Record;
import com.bookDairy.repository.BookRepository;
import com.bookDairy.repository.RecordRepository;
import com.bookDairy.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
//        return recordRepository.findOne(recordId);
        Book book = bookRepository.findOne(bookId);
        return book.getRecordList()
                .stream()
                .filter(record -> record.getId() == recordId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no record"));//TODO custom exception and message
    }

    @Override
    public Record save(Long bookId, Record record) {
        Book book = bookRepository.findOne(bookId);

        //save to "record" collection
        record.setBook(book);
        record = recordRepository.save(record);

        //save to "book" collection
        List<Record> records = book.getRecordList();
        if(records == null){records = new ArrayList<>(); }
        records.add(record);
        book.setRecordList(records);
        book = bookRepository.save(book);

        return record;
    }

    @Override
    public Record update(Long bookId, Long id, Record record) {
//        throw new RuntimeException("The update(Record record) method is not yet written.");

        Book book = bookRepository.findOne(bookId);

        //update in "book" collection
        List<Record> records = book.getRecordList();
        if(records == null){records = new ArrayList<>(); }
        records.removeIf(rec -> rec.getId().equals(id));
        records.add(record);
        book.setRecordList(records);
        book = bookRepository.save(book);

        //update in "record" collection
        record.setBook(book);
        record = recordRepository.save(record);

        return record;
    }

    @Override
    public void delete(Long bookId, Long id) {
        Book book = bookRepository.findOne(bookId);

        //delete from "record" collection
        recordRepository.delete(id);

        //delete from "book" collection
        List<Record> records = book.getRecordList();
        if(records == null){records = new ArrayList<>(); }
        records.removeIf(record -> record.getId().equals(id));
        book.setRecordList(records);
        bookRepository.save(book);

    }

    @Override
    public List<Record> getAll(Long bookId) {
        Book book = bookRepository.findOne(bookId);
        return book.getRecordList();
    }
}
