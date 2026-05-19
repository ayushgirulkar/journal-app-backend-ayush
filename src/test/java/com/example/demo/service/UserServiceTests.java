package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.reposiratory.UserReposiratory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserReposiratory userReposiratory;

    @Test
    public void testFindByUserName1()
    {

        assertNotNull(userReposiratory.findByUserName("ayush"));
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "ayush",
                    "radha",
                    "leo"
            }
    )
    public void testFindByUserName2(String name)
    {

        assertNotNull(userReposiratory.findByUserName(name),"failed for testing the : "+name);
    }


    @Disabled
    @Test
    public void testMarks()
    {
        assertEquals(40,15+25);
    }


}
