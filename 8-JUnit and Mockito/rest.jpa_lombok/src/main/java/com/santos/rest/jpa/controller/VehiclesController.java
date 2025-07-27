package com.santos.rest.jpa.controller;

import com.santos.rest.jpa.entity.Vehicles;
import com.santos.rest.jpa.service.VehicleServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicles")
public class VehiclesController {

    private VehicleServiceImpl vehicleService;


    @PostMapping
    public Vehicles createVehicles(@RequestBody Vehicles vehicles){
        return vehicleService.createVehicle(vehicles);
    }

    @GetMapping
    public List<Vehicles> readAllVehicles(){
        return vehicleService.readAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicles readVehicleById(@PathVariable int id) {
        return vehicleService.readVehicleById(id);
    }

    @PutMapping("/{id}")
    public Vehicles updateVehicleById(@PathVariable int id, @RequestBody Vehicles updatedVehicle){
        return vehicleService.updateVehicleById(id, updatedVehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicleById(@PathVariable int id) {
        vehicleService.deleteVehicleById(id);
    }
}
