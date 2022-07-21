package com.liujinhang.demo.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liujinhang.demo.entity.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnglishGreeting implements GreetingLogicInterface{

    @Autowired
    public Greeting greeting = null;

    @Override
    public String sayGreeting(String name){

        greeting.setId(2);
        greeting.setContent("Hello, " + name);

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(greeting);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
}
