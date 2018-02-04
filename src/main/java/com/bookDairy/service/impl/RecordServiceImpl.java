package com.bookDairy.service.impl;

import com.bookDairy.domain.Book;
import com.bookDairy.domain.Record;
import com.bookDairy.repository.BookRepository;
import com.bookDairy.repository.IdCounterRepository;
import com.bookDairy.repository.RecordRepository;
import com.bookDairy.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:errormessages.properties")
public class RecordServiceImpl implements RecordService{

    private final RecordRepository recordRepository;
    private final BookRepository bookRepository;
    private final IdCounterRepository idCounterRepository;

    @Value("${error.noBookFind}")
    private String noBookFind;

    @Value("${error.noRecordFind}")
    private String noRecordFind;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository, BookRepository bookRepository, IdCounterRepository idCounterRepository) {
        this.recordRepository = recordRepository;
        this.bookRepository = bookRepository;
        this.idCounterRepository = idCounterRepository;
    }

    @Override
    public Record get(Long bookId, Long id) {
        if (!bookRepository.exists(bookId)) {throw new RuntimeException(noBookFind);}
        if (!recordRepository.exists(id)) {throw new RuntimeException(noRecordFind);}

        return recordRepository.findOne(id);
    }

    //!!! Now the id is auto-generated. If you add the id manually, the document may be overwritten
    @Override
    public Record save(Long bookId, Record record) {
        if (!bookRepository.exists(bookId)) {throw new RuntimeException(noBookFind);}
        Book book = bookRepository.findOne(bookId);

        //set next id to record
        if(record.getId() == null || !recordRepository.exists(record.getId())){
            record.setId(idCounterRepository.getNextSequence("record"));
        }

        //save to "record" collection
        record.setBook(book);
        return recordRepository.save(record);
    }

    @Override
    public Record update(Long bookId, Long id, Record record) {
        if (!bookRepository.exists(bookId)) {throw new RuntimeException(noBookFind);}
        if (!recordRepository.exists(id)) {throw new RuntimeException(noRecordFind);}

        Book book = bookRepository.findOne(bookId);

        //update in "record" collection
        record.setBook(book);
        return recordRepository.save(record);
    }

    @Override
    public void delete(Long bookId, Long id) {
        if (!bookRepository.exists(bookId)) {throw new RuntimeException(noBookFind);}
        if (!recordRepository.exists(id)) {throw new RuntimeException(noRecordFind);}

        //delete from "record" collection
        recordRepository.delete(id);

    }

    @Override
    public List<Record> getAll(Long bookId) {
        if (!bookRepository.exists(bookId)) {throw new RuntimeException(noBookFind);}

        return recordRepository.findAll().stream()
                .filter(record -> record.getBook()
                .getId()==bookId)
                .collect(Collectors.toList());
    }
}
