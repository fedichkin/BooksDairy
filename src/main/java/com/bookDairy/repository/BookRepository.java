package com.bookDairy.repository;

import com.bookDairy.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Maryna Kontar.
 */
//@Repository
public interface BookRepository extends MongoRepository<Book,Long>{
    List<Book> findAllByCategory(String category);

}
