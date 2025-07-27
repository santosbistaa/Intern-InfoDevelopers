package com.jparelationship.jparelationship.Repo;

import com.jparelationship.jparelationship.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, String> {
}
