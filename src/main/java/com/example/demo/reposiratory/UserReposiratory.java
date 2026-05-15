package com.example.demo.reposiratory;

import com.example.demo.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserReposiratory extends MongoRepository<User, ObjectId> {

   User findByUserName(String username);


    void deleteByUserName(String usernmae);
}

