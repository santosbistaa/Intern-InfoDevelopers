package com.santos.rest.jpa.service;

import com.santos.rest.jpa.entity.Vehicles;
import com.santos.rest.jpa.repo.VehiclesRepoitory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private VehiclesRepoitory vehiclesRepoitory;


    @Override
    public Vehicles createVehicle(Vehicles vehicles) {
        return vehiclesRepoitory.save(vehicles);
    }

    @Override
    public List<Vehicles> readAllVehicles() {
        return vehiclesRepoitory.findAll();
    }

    @Override
    public Vehicles readVehicleById(int id) {
        return vehiclesRepoitory.findById(id).orElse(null);
    }

    @Override
    public Vehicles updateVehicleById(int id, Vehicles updatedVehicle) {
        return vehiclesRepoitory.findById(id).map(vehicles -> {
            vehicles.setYear(vehicles.getYear());
            vehicles.setModel(vehicles.getModel());
            return vehiclesRepoitory.save(updatedVehicle);
        }).orElse(null);
    }

    @Override
    public void deleteVehicleById(int id) {
        vehiclesRepoitory.deleteById(id);
    }
}
