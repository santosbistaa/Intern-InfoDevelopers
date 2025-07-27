package com.jpaworkshop.santos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String streetName;

    private String houseNumber;

    private String zipCode;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
