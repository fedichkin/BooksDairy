package com.bookDairy.service;

import com.bookDairy.domain.Record;

import java.util.List;

/**
 * Created by Maryna Kontar.
 */
public interface RecordService {

    Record get(Long bookId, Long recordId);
    Record save(Long bookId, Record record);
    Record update(Long bookId, Long id, Record record);
    void delete(Long bookId, Long id);
    List<Record> getAll(Long bookId);
}
