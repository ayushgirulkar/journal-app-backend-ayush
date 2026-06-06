package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserDetailsServiceImpl;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @Autowired
    private  UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  JwtUtil jwtUtil;

    @PostMapping("/signup")//signup
    public void createUser1(@RequestBody User user)
    {
        userService.saveNewUser(user);
    }
    @PostMapping("/create-user2")//login
    public ResponseEntity<String> createUser2(@RequestBody User user)
    {
        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPasswords())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt= jwtUtil.generateToken(userDetails.getUsername());

            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.error("Exception occur while crateAuthenticationToken",e);
            return  new ResponseEntity<>("Incorrect username or Passwords",HttpStatus.BAD_REQUEST);

        }

    }
}
