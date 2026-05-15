package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.reposiratory.UserReposiratory;
import com.example.demo.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserReposiratory userReposiratory;

    @PutMapping
    public ResponseEntity<?>updateUser(@RequestBody User user)
     {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String userName = authentication.getName();

         User  userInDb = userService.findByUserName(userName);

             userInDb.setUserName(user.getUserName());
             userInDb.setPasswords(user.getPasswords());
             userService.saveNewUser(userInDb);

         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
    @DeleteMapping
    public ResponseEntity<?>deleteUser(@RequestBody User user)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userReposiratory.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
