package com.ocms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String title;

   private String description;

   @ManyToOne
    private User instructor;

   @ManyToMany
    private Set<User> students = new HashSet<>();

   @OneToMany(mappedBy = "course")
    private List<CourseMaterial> materials = new ArrayList<>();
}
