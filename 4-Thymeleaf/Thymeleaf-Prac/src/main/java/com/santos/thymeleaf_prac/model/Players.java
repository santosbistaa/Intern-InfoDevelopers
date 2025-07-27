package com.santos.thymeleaf_prac.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Players {
    private String name;
    private String position;
    private int age;
    private int salary;
}
