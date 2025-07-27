package com.santos.thymeleaf.controller;

import com.santos.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/variable-expression")
    public String variableExpression(Model model) {
        User user = new User("Sagar Thapa","sagar@email.com","Admin","Male");
        model.addAttribute("user",user);
        return "variable-expression";
    }

    @GetMapping("/selection-expression")
    public String selectionExpression(Model model) {
        User user = new User("Santos Bista","santos@email.com","Manager","Male");
        model.addAttribute("user",user);
        return "selection-expression";
    }

    @GetMapping("/message-expression")
    public String messageExpression() {
        return "message-expression";
    }

    @GetMapping("/link-expression")
    public String linkExpression(){
        return "link-expression";
    }

    @GetMapping("/fragment-expression")
    public String fragmentExpression(){
        return "fragment-expression";
    }

    @GetMapping("/users")
    public String users(Model model) {
        User user1 = new User("John Doe","john@email.com","CEO","Male");
        User user2 = new User("Kim Un","kim@email.com","Manager","Male");
        User user3 = new User("Mina Rai","mina@email.com","HR","Female");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        model.addAttribute("users",users);
        return "users";
    }

    @GetMapping("/if-unless")
    public String ifUnless(Model model){
        User user1 = new User("Joe Hart","joe@email.com","Team Lead","Male");
        User user2 = new User("Alisha Lehmann","alsha@email.com","IT","Female");
        User user3 = new User("Phil Jones","phil@email.com","IT","Male");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        model.addAttribute("users",users);

        return "if-unless";
    }

    @GetMapping("/switch")
    public String switchCase(Model model){
        User user = new User(
                "Raphinha",
                "rap@email.com",
                "Manager",
                "Male");
        model.addAttribute("user",user);
        return "switch";
    }
}
