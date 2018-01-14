package com.bookDairy.repository;

import com.bookDairy.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Maryna Kontar.
 */
@Repository
public interface BookRepository extends MongoRepository<Book,Long>{


}
