package com.example.demo.controller;

import com.example.demo.api.response.WheatherResponse;
import com.example.demo.entity.User;
import com.example.demo.reposiratory.UserReposiratory;
import com.example.demo.service.UserService;
import com.example.demo.service.WeatherService;
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
    WeatherService weatherService;

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
    @GetMapping
    public ResponseEntity<?> greeting()
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        WheatherResponse wheatherResponse=weatherService.getWeather("Amravati");
        String greeting ="aaj ka weather kl jaisa rahega";
        if(wheatherResponse!=null)
        {
            greeting=",Today's Wheather : "+wheatherResponse.getCurrent().getTemperature();
        }
        return new ResponseEntity<>("Hi " +authentication.getName() +greeting, HttpStatus.OK);
    }


}
