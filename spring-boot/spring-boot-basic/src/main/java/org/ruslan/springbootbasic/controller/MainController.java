package org.ruslan.springbootbasic.controller;

import org.ruslan.springbootbasic.bean.ObjectCreator;
import org.ruslan.springbootbasic.model.Message;
import org.ruslan.springbootbasic.model.User;
import org.ruslan.springbootbasic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {


    ObjectCreator createObject;
    UserService userService;

    @Autowired
    public MainController(ObjectCreator createObject, UserService userService) {
        this.createObject = createObject;
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping("/hello")
    public String getHelloMessage() {
        return "Hello";
    }

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    @ResponseBody   // по /user должен отправляться user.toString()
    @GetMapping("/user1")
    public String getUser1() {
        return new User("Ivan", 22).toString();
    }

    @ResponseBody   // по /message должно отправляться message.toString()
    @GetMapping("/message1")
    public String getMessage1() {
        return new Message("Hello", true).toString();
    }

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //Создать отдельный класс (компонент), который будет предоставлять два метода: createUser, createMessage.
    @ResponseBody
    @GetMapping("/user")
    public String getUser() {
        return createObject.createUser().toString();
    }

    @ResponseBody
    @GetMapping("/message")
    public String getMessage() {
        return createObject.createMessage().toString();
    }

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //Написать страницу с отображением List<User>.
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
}
