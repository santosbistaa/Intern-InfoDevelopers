package com.santos.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World!!");
        return "hello";
    }
}
