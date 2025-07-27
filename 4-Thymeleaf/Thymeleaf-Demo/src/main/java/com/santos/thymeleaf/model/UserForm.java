package com.santos.thymeleaf.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserForm {

    private String name;
    private String email;
    private String password;
    private String gender;
    private String address;
    private String married;
    private String profession;
}
