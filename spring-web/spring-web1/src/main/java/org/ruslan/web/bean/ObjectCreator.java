package org.ruslan.web.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ObjectCreator {

    @Value("${u.name}")
    String name;
    @Value("${u.age}")
    Integer age;


    @Autowired
    public User createUser( ) {

        return new User(name, age);
    }

    @Autowired
    public Message createMessage() {
        return new Message("text", true);
    }

    public ObjectCreator() {
    }
}
