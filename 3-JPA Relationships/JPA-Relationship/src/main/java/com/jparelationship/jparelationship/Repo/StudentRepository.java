package com.jparelationship.jparelationship.Repo;

import com.jparelationship.jparelationship.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
