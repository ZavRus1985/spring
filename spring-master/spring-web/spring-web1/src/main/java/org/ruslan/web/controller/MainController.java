package org.ruslan.web.controller;

import org.ruslan.web.bean.ObjectCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    ObjectCreator createObject;

    @ResponseBody   // пункт 4 в задании
    @GetMapping("/user")
    public String getUser() {
        return createObject.createUser().toString();
    }

    @ResponseBody   // пункт 3 в задании
    @GetMapping("/message")
    public String getMessage() {
        return createObject.createMessage().toString();
    }

    @ResponseBody
    @GetMapping("/hello")
    public String getHelloMessage() {
        return "Hello";
    }

}
