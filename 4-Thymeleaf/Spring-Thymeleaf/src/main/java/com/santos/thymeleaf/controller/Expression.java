package com.santos.thymeleaf.controller;

import com.santos.thymeleaf.model.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Expression {

    @GetMapping("/variable-expression")
    public String variableExpression(Model model){
        Player player =  new Player("Leo Messi","AMF",33,"Barcelona");
        model.addAttribute("player",player);
        return "variable-expression";
    }

    @GetMapping("/selection-expression")
    public String selectionExpression(Model model){
        Player player =  new Player("Neymar Jr","LWF",25,"Barcelona");
        model.addAttribute("player",player);
        return "selection-expression";
    }

    @GetMapping("/message-expression")
    public String messageExpression(){
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
}
