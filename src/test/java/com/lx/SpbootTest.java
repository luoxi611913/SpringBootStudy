package com.lx;

import com.lx.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpbootTest {

    @Autowired(required = false)
    Person person;

    @Test
    public void testYaml(){

        System.out.println(person);
    }
}