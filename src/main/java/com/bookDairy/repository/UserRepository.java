package com.bookDairy.repository;

import com.bookDairy.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.CrudRepository;

/**
 * Created by Maryna Kontar.
 */
@Repository
public interface UserRepository extends MongoRepository<User,Long>{
    User findByUsername(String username);

    User findByEmail(String email);
}
