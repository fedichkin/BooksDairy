package com.bookDairy.service.impl;

import com.bookDairy.domain.User;
import com.bookDairy.repository.UserRepository;
import com.bookDairy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Created by Maryna Kontar.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(String username) {
        return userRepository.findOne(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    //TODO
    @Override
    public User update(User user) {
        throw new RuntimeException("The update(User user) method is not yet written.");
    }

    @Override
    public void delete(String username) {
        userRepository.delete(username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}


