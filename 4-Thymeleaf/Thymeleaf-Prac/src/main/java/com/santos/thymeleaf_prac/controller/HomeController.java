package com.santos.thymeleaf_prac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/hello")
    public String greet(Model model){
        model.addAttribute("message","Hello User!!!");
        return "hello";
    }

}
