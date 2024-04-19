package org.ruslan.web.bean;

import org.ruslan.web.model.Message;
import org.ruslan.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ObjectCreator {

    @Value("${u.name}")
    String name;
    @Value("${u.age}")
    Integer age;

    @Value("${m.text}")
    String text;
    @Value("${m.check}")
    Boolean check;


    @Autowired
    public User createUser( ) {
        return new User(name, age);
    }

    @Autowired
    public Message createMessage() {
        return new Message(text, check);
    }

}
