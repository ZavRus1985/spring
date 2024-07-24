package org.ruslan.springbootbasic.bean;

import org.ruslan.springbootbasic.model.Message;
import org.ruslan.springbootbasic.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ObjectCreator {

    @Value("${u.name}")
    private String name;
    @Value("${u.age}")
    private Integer age;
//
//    @Value("${message.text}")
//    private String text;
//    @Value("${message.check}")
//    private Boolean check;

    @Autowired
    public User createUser( ) {
        return new User(name, age);
    }

    @Autowired
    public Message createMessage() {
        return new Message("text", true);
    }

}

