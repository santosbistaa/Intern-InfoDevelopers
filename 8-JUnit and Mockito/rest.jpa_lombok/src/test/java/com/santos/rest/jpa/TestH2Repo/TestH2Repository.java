package com.santos.rest.jpa.TestH2Repo;

import com.santos.rest.jpa.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Vehicles,Integer> {
}
