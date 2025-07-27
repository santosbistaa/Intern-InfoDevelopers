package com.jparelationship.jparelationship.Repo;

import com.jparelationship.jparelationship.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, String> {
}
