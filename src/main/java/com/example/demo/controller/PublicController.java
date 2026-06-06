package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @PostMapping("/create-user1")//signup
    public void createUser(@RequestBody User user)
    {
        userService.saveNewUser(user);
    }
    @PostMapping("/create-user2")//login
    public void createUser(@RequestBody User user)
    {
        userService.saveNewUser(user);
    }
}
