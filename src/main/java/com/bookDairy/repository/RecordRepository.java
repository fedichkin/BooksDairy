package com.bookDairy.repository;

import com.bookDairy.domain.Record;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by Maryna Kontar.
 */
public interface RecordRepository extends MongoRepository<Record, Long> {
}
