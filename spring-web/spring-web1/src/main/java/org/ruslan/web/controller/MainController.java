package org.ruslan.web.controller;

import org.ruslan.web.entity.CreateObject;
import org.ruslan.web.entity.Message;
import org.ruslan.web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    CreateObject createObject;

    @ResponseBody   // пункт 4 в задании
    @GetMapping("/user")
    public String geUser() {
        return createObject.createUser().toString();
    }

//    @ResponseBody   // пункт 3 в задании
//    @GetMapping("/message")
//    public String getMessage() {
//        return new Message("TeXt-Message", true).toString();
//    }
//
//    @ResponseBody
//    @GetMapping("/hello")
//    public String getHelloMessage() {
//        return "Hello";
//    }

}
