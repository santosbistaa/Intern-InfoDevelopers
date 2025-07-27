package com.santos.rest.jpa.service;

import com.santos.rest.jpa.entity.Vehicles;

import java.util.List;

public interface VehicleService {

    // Create
     Vehicles createVehicle(Vehicles vehicles);

    // Read All
    List<Vehicles> readAllVehicles();

    // Read By id
    Vehicles readVehicleById(int id);

    // Update By id
    Vehicles updateVehicleById(int id, Vehicles updatedVehicle);

    // Delete By id
     void deleteVehicleById(int id);
}
