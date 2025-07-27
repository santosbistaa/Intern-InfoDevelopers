package com.santos.rest.jpa.repo;

import com.santos.rest.jpa.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclesRepoitory extends JpaRepository<Vehicles, Integer> {
}
