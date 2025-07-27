package com.santos.thymeleaf.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private String name;
    private String email;
    private String role;
    private String gender;
}
