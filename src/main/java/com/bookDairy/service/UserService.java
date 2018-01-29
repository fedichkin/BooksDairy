package com.bookDairy.service;

import com.bookDairy.domain.User;

/**
 * Created by Maryna Kontar.
 */
public interface UserService {

    User get(String username);
    User save(User user);
    User update(User user);
    void delete(String username);
    User findByUsername(String username);
    User findByEmail(String email);

}
