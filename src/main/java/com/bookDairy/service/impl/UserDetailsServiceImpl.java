package com.bookDairy.service.impl;

import com.bookDairy.domain.User;
import com.bookDairy.domain.UserDetailsImpl;
import com.bookDairy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation for user {@link com.bookDairy.domain.User} detailed service which will use
 * {@link com.bookDairy.service.UserService} as users repository.
 *
 * @KontarMaryna
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Implementation of {@link UserDetailsService} method
     *
     * @param username identifying the user whose data is required
     * @return a {@link UserDetailsImpl} if such {@link User} exists
     * @throws UsernameNotFoundException if user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + "is not found");
        }
        return new UserDetailsImpl(user);
    }
}