package com.ocms.repository;

import com.ocms.entities.Role;
import com.ocms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    // finds a user by their email
    Optional<User> findByEmail(String email);

    // find all the users with a specific role
    List<User> findByRole(Role role);
}
