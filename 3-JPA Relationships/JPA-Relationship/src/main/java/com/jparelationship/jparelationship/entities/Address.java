package com.jparelationship.jparelationship.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    private int addressId;

    private String street;

    private String city;

    private String country;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
