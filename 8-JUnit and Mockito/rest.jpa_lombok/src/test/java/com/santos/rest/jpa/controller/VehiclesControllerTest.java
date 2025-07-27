package com.santos.rest.jpa.controller;

import com.santos.rest.jpa.entity.Vehicles;
import com.santos.rest.jpa.service.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehiclesControllerTest {
    @Mock
    VehicleServiceImpl vehicleService;

    @InjectMocks
    VehiclesController vehiclesController;

    private Vehicles vehicle1;
    private Vehicles vehicle2;

    @BeforeEach
    void setup() {
        vehicle1 = new Vehicles(1, "Ford", "Ranger", 2000);
        vehicle2 = new Vehicles(2,"Toyota","Hilux",2021);
    }

    @Test
    void createVehicles(){
        when(vehicleService.createVehicle(vehicle1)).thenReturn(vehicle1);

        Vehicles vehicleCreated = vehiclesController.createVehicles(vehicle1);

        assertEquals(1,vehicleCreated.getId());
        assertEquals("Ford",vehicleCreated.getBrand());

        verify(vehicleService, times(1)).createVehicle(vehicleCreated);
    }

    @Test
    void readAllVehicles(){
        when(vehicleService.readAllVehicles()).thenReturn(Arrays.asList(vehicle1,vehicle2));

        List<Vehicles> vehiclesList = vehiclesController.readAllVehicles();

        assertEquals(2,vehiclesList.size());

        verify(vehicleService,times(1)).readAllVehicles();
    }

    @Test
    void readVehiclesById(){
        when(vehicleService.readVehicleById(1)).thenReturn(vehicle1);

        Vehicles readingByIdVehicle = vehiclesController.readVehicleById(1);

        assertEquals(1,vehicle1.getId());

        verify(vehicleService,times(1)).readVehicleById(1);
    }

    @Test
    void updateVehicleById(){

        Vehicles updatedVehicle = new Vehicles(1,"Mahindra","Thar",2021);

        when(vehicleService.updateVehicleById(1,updatedVehicle)).thenReturn(updatedVehicle);

        Vehicles updatingVehicles = vehiclesController.updateVehicleById(1,updatedVehicle);
        assertEquals("Mahindra",updatingVehicles.getBrand());

        verify(vehicleService,times(1)).updateVehicleById(1,updatingVehicles);
    }

    @Test
    void deleteVehicleById(){
        Vehicles ve = Vehicles.builder()
                .id(9)
                .build();

         vehiclesController.deleteVehicleById(9);

        Vehicles read =  vehiclesController.readVehicleById(9);

        assertNull(read);

        verify(vehicleService,times(1)).deleteVehicleById(9);

    }































//    @Mock
//    VehicleServiceImpl vehicleService;
//
//    @InjectMocks
//    VehiclesController controller;
//
//
////    @BeforeEach
////    void setup() {
////        Vehicles vehicle1 = new Vehicles(1, "Mercedes", "Benz", 2000);
////        Vehicles vehicle2 = new Vehicles(2, "Ford", "Ranger", 2002);
////    }
//    private Vehicles vehicle1;
//    private Vehicles vehicle2;
//
//    @BeforeEach
//    void setup() {
//        vehicle1 = new Vehicles(1, "Mercedes", "Benz", 2000);
//        vehicle2 = new Vehicles(2, "Ford", "Ranger", 2002);
//    }
//
//    @Test
//    void createVehicles() {
//
//        when(vehicleService.createVehicle(vehicle1)).thenReturn(vehicle1);
//
//        Vehicles result = controller.createVehicles(vehicle1);
//
//        assertEquals(1,vehicle1.getId());
//        assertEquals("Mercedes",result.getBrand());
//
//        verify(vehicleService, times(1)).createVehicle(vehicle1);
//
//    }
//
//    @Test
//    void readAllVehicles() {
//
//        when(vehicleService.readAllVehicles()).thenReturn(Arrays.asList(vehicle1,vehicle2));
//
//        List<Vehicles> vehiclesList = controller.readAllVehicles();
//
//        assertEquals(2,vehiclesList.size());
//        assertEquals("Ford",vehicle2.getBrand());
//
//        verify(vehicleService,times(1)).readAllVehicles();
//    }
//
//    @Test
//    void readVehicleById() {
//
//        when(vehicleService.readVehicleById(1)).thenReturn(vehicle1);
//
//        Vehicles readById = controller.readVehicleById(1);
//
//        assertEquals(1,readById.getId());
//        assertEquals("Mercedes",readById.getBrand());
//
//        verify(vehicleService,times(1)).readVehicleById(1);
//    }
//
//    @Test
//    void updateVehicleById() {
//        Vehicles updateVehicle = new Vehicles(1,"Mahindra","Thar",2006);
//        when(vehicleService.updateVehicleById(1,updateVehicle)).thenReturn(updateVehicle);
//
//        Vehicles updatedVehicleById = controller.updateVehicleById(1,updateVehicle);
//
//        assertEquals("Mahindra",updatedVehicleById.getBrand());
//        assertTrue(2006 == updatedVehicleById.getYear());
//
//        verify(vehicleService,times(1)).updateVehicleById(1,updatedVehicleById);
//    }
//
//    @Test
//    void deleteVehicleById() {
//
//        Vehicles ve = Vehicles.builder()
//                .id(9)
//                .build();
//
//        doNothing().when(vehicleService).deleteVehicleById(9);
//
//        controller.deleteVehicleById(9);
//
//        Vehicles read = vehicleService.readVehicleById(9);
//
//        assertNull(read);
//
//        verify(vehicleService,times(1)).deleteVehicleById(9);
//    }

}