package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Submission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;
    private String grade;
    private String feedback;

    private LocalDateTime submittedAt;

    @ManyToOne
    private Assignment assignment;

    @ManyToOne
    private User student;

}
