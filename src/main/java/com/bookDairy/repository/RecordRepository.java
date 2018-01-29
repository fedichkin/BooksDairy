package com.bookDairy.repository;

import com.bookDairy.domain.Record;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Maryna Kontar.
 */
@Repository
public interface RecordRepository extends MongoRepository<Record, Long> {
}
