package org.ruslan.web.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateObject {

    @Autowired
    public User createUser() {
        return new User();
    }

    @Autowired
    public Message createMessage() {
        return new Message("text", true);
    }

    public CreateObject() {
    }


}
