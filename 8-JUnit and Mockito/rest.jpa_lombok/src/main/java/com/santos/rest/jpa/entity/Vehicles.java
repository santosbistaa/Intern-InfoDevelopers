package com.santos.rest.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@Table(name = "vehicles")
public class Vehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    // --> enable this while integration testing
    @Column(name = "vehicle_year")

    // --> otherwise enable this
//    @Column(name = "year")
    private int year;

    public Vehicles(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
}
