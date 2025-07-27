package com.santos.thymeleaf_prac.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerForm {
    private String name;
    private String position;
    private int age;
    private String strongLeg;
    private String club;
}
