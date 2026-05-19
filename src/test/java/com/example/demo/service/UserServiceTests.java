package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.reposiratory.UserReposiratory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserReposiratory userReposiratory;

    @Test
    public void testFindByUserName()
    {

        assertNotNull(userReposiratory.findByUserName("ayush"));
    }

    @Test
    public void testMarks()
    {
        assertEquals(40,15+25);
    }


}
