package com.santos.thymeleaf.controller;

import com.santos.thymeleaf.model.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class PlayerForm {

    @GetMapping("/register")
    public String playerForm(Model model){
        Player player = new Player();
        model.addAttribute("player", player);

        List<String> positions = Arrays.asList("Striker","MidFielder","Defender","Goalkeeper");
        model.addAttribute("positions",positions);
        return "register";
    }

    @PostMapping("/register/save")
    public String save(Model model,
                       @ModelAttribute ("player")Player player){
        model.addAttribute("player",player);
        return "player-registered";
    }
}
