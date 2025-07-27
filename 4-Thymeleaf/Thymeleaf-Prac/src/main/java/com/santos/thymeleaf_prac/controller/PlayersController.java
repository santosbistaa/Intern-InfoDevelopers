package com.santos.thymeleaf_prac.controller;

import com.santos.thymeleaf_prac.model.Players;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlayersController {
    @GetMapping("/variable-expression")
    public String variableExpression(Model model){
        Players players = new Players("Kevin DeBruyne","AMF",32,2000);
        model.addAttribute("players",players);
        return "variable-expression";

    }

    @GetMapping("/selection-expression")
    public String selectionExpression(Model model){
        Players players = new Players("Sergio Aguero","CF",33,5000);
        model.addAttribute("players",players);
        return "selection-expression";
    }

    @GetMapping("/message-expression")
    public String messageExpression(){
        return "message-expression";
    }

    @GetMapping("/link-expression")
    public String linkExpression() {
        return "link-expression";
    }

    @GetMapping("/fragment-expression")
    public String fragmentExpression(){
        return "fragment-expression";
    }

    @GetMapping("/players")
    public String players(Model model){
        Players player1 = new Players("Vincent Kompany","CB",42,3500);
        Players player2 = new Players("David Silva","CMF",39,3000);
        Players player3 = new Players("Erling Haaland","CF",23,7000);

        List<Players> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        model.addAttribute("players",players);
        return "players";
    }

    @GetMapping("/if-unless")
    public String ifUnless(Model model){
        Players player1 = new Players("Ederson","GK",35,4500);
        Players player2 = new Players("Hart","GK",40,2500);
        Players player3 = new Players("Cancelo","LB",37,3000);

        List<Players> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        model.addAttribute("players",players);

        return "if-unless";
    }

    @GetMapping("/switch")
    public String switchCase(Model model){
        Players player = new Players("Jack","LWF",27,3000);
        model.addAttribute("player",player);
        return "switch";
    }
}
