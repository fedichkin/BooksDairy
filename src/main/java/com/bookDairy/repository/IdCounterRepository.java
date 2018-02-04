package com.bookDairy.repository;

import com.bookDairy.domain.IdCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class IdCounterRepository {

    @Autowired
    private MongoOperations mongoOperations;

    public Long getNextSequence(String collectionName) {
        IdCounter counter = mongoOperations.findAndModify(
                query(where("_id").is(collectionName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                IdCounter.class);
        if(counter == null) throw new RuntimeException("Unable to get sequence for key: " + collectionName);

        return counter.getSeq();
    }
}
