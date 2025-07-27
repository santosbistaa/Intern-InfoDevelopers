package com.santos.rest.jpa.service;

import com.santos.rest.jpa.entity.Vehicles;
import com.santos.rest.jpa.repo.VehiclesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private VehiclesRepository vehiclesRepository;


    @Override
    public Vehicles createVehicle(Vehicles vehicles) {
        return vehiclesRepository.save(vehicles);
    }

    @Override
    public List<Vehicles> readAllVehicles() {
        return vehiclesRepository.findAll();
    }

    @Override
    public Vehicles readVehicleById(int id) {
        return vehiclesRepository.findById(id).orElse(null);
    }

    @Override
//    public Vehicles updateVehicleById(int id, Vehicles updatedVehicle) {
//        return vehiclesRepository.findById(id).map(vehicles -> {
//            vehicles.setYear(vehicles.getYear());
//            vehicles.setModel(vehicles.getModel());
//            return vehiclesRepository.save(updatedVehicle);
//        }).orElse(null);
//
//    }
    public Vehicles updateVehicleById(int id, Vehicles vehicle) {
        Vehicles existingVehicle = vehiclesRepository.findById(id).orElse(null);
        existingVehicle.setBrand(vehicle.getBrand());
        existingVehicle.setModel(vehicle.getModel());
        existingVehicle.setYear(vehicle.getYear());
        return vehiclesRepository.save(existingVehicle);

    }

    @Override
    public void deleteVehicleById(int id) {
        vehiclesRepository.deleteById(id);
    }
}
