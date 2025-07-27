package com.santos.thymeleaf.controller;

import com.santos.thymeleaf.model.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Iterations {

    @GetMapping("/iterations")
    public String iterations(Model model){
        Player player1 = new Player("Xavi","CMF",30,"Barcelona");
        Player player2 = new Player("David Silva","CMF",31,"Manchester City");
        Player player3 = new Player("Seedorf","CMF",33,"AC Milan");

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        model.addAttribute("players",players);
        return "iterations";
    }
}
