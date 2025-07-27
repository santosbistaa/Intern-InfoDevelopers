package com.santos.thymeleaf_prac.controller;

import com.santos.thymeleaf_prac.model.PlayerForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class FormController {

    @GetMapping("/register")
    public String registerPlayer(Model model) {
        PlayerForm playerForm = new PlayerForm();
        model.addAttribute("playerForm", playerForm);

        List<String> positionList = Arrays.asList("Striker","Midfielder","Defender","Goalkeeper");
        model.addAttribute("positionList", positionList);
        return "register-form";
    }

    @PostMapping("/register/save")
    public String registerSuccess(Model model,
                                  @ModelAttribute("playerForm")PlayerForm playerForm){
        model.addAttribute("playerForm", playerForm);
        return "register-success";
    }
}
