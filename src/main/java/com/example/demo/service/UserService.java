package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.reposiratory.UserReposiratory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private  UserReposiratory userReposiratory;

    public void saveEntry(User user)
    {
        userReposiratory.save(user);
    }
    public List<User>getAll()
    {
        return userReposiratory.findAll();
    }
    public Optional<User> findById(ObjectId id)
    {
        return userReposiratory.findById(id);
    }
    public void deleteById(ObjectId id)
    {
        userReposiratory.deleteById(id);
    }
    public User findByUserName(String userName)
    {
        return userReposiratory.findByUserName(userName);
    }





}
