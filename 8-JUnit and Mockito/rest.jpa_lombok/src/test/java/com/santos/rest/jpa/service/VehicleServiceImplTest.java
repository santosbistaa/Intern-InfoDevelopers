package com.santos.rest.jpa.service;

import com.santos.rest.jpa.entity.Vehicles;
import com.santos.rest.jpa.repo.VehiclesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {

    @Mock
    VehiclesRepository vehiclesRepository;
    @InjectMocks
    VehicleServiceImpl vehicleServiceImpl;


//    static Vehicles vehicles = null;
//    @BeforeAll
//    public static void init(){
//        vehicles = new Vehicles();
//        vehicles.setId(1);
//        vehicles.setBrand("Mercedes");
//        vehicles.setModel("Benz");
//        vehicles.setYear(2000);
//    }

    private Vehicles vehicle1;
    private Vehicles vehicle2;

    @BeforeEach
    void setup() {
        vehicle1 = new Vehicles(1, "Mercedes", "Benz", 2000);
        vehicle2 = new Vehicles(2, "Ford", "Ranger", 2002);
    }

    @Test
    void createVehicle() {

        // Mocking calls
        when(vehiclesRepository.save(vehicle1)).thenReturn(vehicle1);

        // calling actual methods
        Vehicles createdVehicles = vehicleServiceImpl.createVehicle(vehicle1);

        // assertions
        assertEquals(vehicle1.getId(), createdVehicles.getId());
        assertEquals(vehicle1.getModel(), createdVehicles.getModel());
        assertNotNull(createdVehicles);
        assertTrue(createdVehicles.getYear() == 2000);

        verify(vehiclesRepository, times(1)).save(vehicle1);
    }

    @Test
    void readAllVehicles() {
        when(vehiclesRepository.findAll()).thenReturn(Arrays.asList(vehicle1, vehicle2));

        List<Vehicles> vehiclesList = vehicleServiceImpl.readAllVehicles();

        assertEquals(2, vehiclesList.size());
        assertEquals(2, vehicle2.getId());

        verify(vehiclesRepository, times(1)).findAll();
    }

    @Test
    void readVehicleById() {
        when(vehiclesRepository.findById(2)).thenReturn(Optional.ofNullable(vehicle2));

        var readingVehiclesById = vehicleServiceImpl.readVehicleById(2);

        assertEquals(2, readingVehiclesById.getId());
        assertTrue(readingVehiclesById.getModel() == "Ranger");
        verify(vehiclesRepository, times(1)).findById(2);
    }

    @Test
    void updateVehicleById() {
        Vehicles updatedVehicle = new Vehicles(1, "Mahindra", "Thar", 2021);
        when(vehiclesRepository.findById(1)).thenReturn(Optional.ofNullable(vehicle1));
        when(vehiclesRepository.save(updatedVehicle)).thenReturn(updatedVehicle);

        Vehicles vehicleUpdated = vehicleServiceImpl.updateVehicleById(1, updatedVehicle);

        assertEquals(1, vehicleUpdated.getId());
        assertEquals("Mahindra", vehicleUpdated.getBrand());

        verify(vehiclesRepository, times(1)).findById(1);
        verify(vehiclesRepository, times(1)).save(updatedVehicle);
    }

    @Test
    void deleteVehicleById() {

        var ve = Vehicles.builder()
                .id(5)
                .build();

        doNothing().when(vehiclesRepository).deleteById(5);

        vehicleServiceImpl.deleteVehicleById(5);
        var result = vehicleServiceImpl.readVehicleById(5);

        assertNull(result);

        verify(vehiclesRepository,times(1)).deleteById(5);

    }
}