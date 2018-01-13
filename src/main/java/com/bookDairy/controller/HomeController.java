package com.bookDairy.controller;

import com.bookDairy.domain.User;
import com.bookDairy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Created by Maryna Kontar.
 */

@RestController
public class HomeController {

//    private final UserService userService;
//
//    @Autowired
//    public HomeController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/")
    public String sayHello(){
        return "Hello, Junior Java Free Start!";
    }

//    @PostConstruct
//    public void createInitUser() {
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("Maryna");
//        user.setFirstName("Maryna");
//        user.setLastName("Kontar");
//        user.setEmail("marina.acoustic@gmail.com");
//
//        userService.save(user);
//    }
}
