package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
     public List<User>getAllUser()
     {
         return userService.getAll();
     }
     @PostMapping
     public void createUser(@RequestBody User user)
     {
         userService.saveEntry(user);
     }

     @PutMapping
    public ResponseEntity<?>uodateUser(@RequestBody User user)
     {
         User  userInDb = userService.findByUserName(user.getUserName());
         if(userInDb!=null)
         {
             userInDb.setUserName(user.getUserName());
             userInDb.setPasswords(user.getPasswords());
             userService.saveEntry(userInDb);
         }
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }


}
