package com.santos.thymeleaf.controller;

import com.santos.thymeleaf.model.UserForm;
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
    public String userRegistration(Model model) {
        //Empty userForm model object to store form data
        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);

        List<String> listProfession = Arrays.asList("Developer","Tester","Architect");
        model.addAttribute("listProfession",listProfession);
        return "register-form";
    }

    @PostMapping("/register/save")
    public String submitForm(Model model,
                             @ModelAttribute("userForm")UserForm userForm) {
        model.addAttribute("userForm",userForm);
        return "register-success";
    }
}
