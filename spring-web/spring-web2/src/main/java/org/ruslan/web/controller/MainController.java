package org.ruslan.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/index")
    public String getIndexPage(Model model) {
        model.addAttribute("name", "Ruslan");
        return "index";
    }

}
