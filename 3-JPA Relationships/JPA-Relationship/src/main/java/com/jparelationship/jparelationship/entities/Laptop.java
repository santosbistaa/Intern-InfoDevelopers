package com.jparelationship.jparelationship.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int laptopId;

    private String modelNumber;

    private String brand;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
