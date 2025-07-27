package com.santos.rest.jpa;

import com.santos.rest.jpa.TestH2Repo.TestH2Repository;
import com.santos.rest.jpa.entity.Vehicles;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class ApplicationTests {

	@LocalServerPort
	private int port;

//	http://localhost:8080/api/vehicles
	private String baseUrl = "http://localhost";

	private static RestTemplate restTemplate;

	@Autowired
	private TestH2Repository h2Repository;

	@BeforeAll
	public static void init(){
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp(){
		baseUrl = baseUrl.concat(":").concat(port+"").concat("/vehicles");
	}

	@Test
	public void testCreateVehicle(){
		Vehicles vehicles = new Vehicles("Tata","Nano",2021);
		Vehicles response = restTemplate.postForObject(baseUrl,vehicles, Vehicles.class);
		assertEquals("Tata",response.getBrand());
		assertEquals(1,h2Repository.findAll().size());

	}

	@Test
	@Sql(statements = "INSERT INTO vehicles (id,brand,model,vehicle_year) VALUES (3,'Mahindra','Thar',2000)",
			executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM vehicles WHERE  brand='Mahindra'"
			,executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadAllVehicles(){
		List<Vehicles> vehiclesList = restTemplate.getForObject(baseUrl, List.class);
		assertEquals(1,vehiclesList.size());
		assertEquals(1,h2Repository.findAll().size());
	}

	@Test
	@Sql(statements = "INSERT INTO vehicles (id,brand,model,vehicle_year) VALUES (1,'BYD','Dolphin',2024)",
			executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM vehicles WHERE  id=1"
			,executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadVehicleById(){
		Vehicles vehicle = restTemplate.getForObject(baseUrl + "/{id}", Vehicles.class, 1);
		assertAll(
				()->assertNotNull(vehicle),
				()->assertEquals(1,vehicle.getId()),
				()->assertEquals("Dolphin",vehicle.getModel())
		);
	}

	@Test
	@Sql(statements = "INSERT INTO vehicles (id,brand,model,vehicle_year) VALUES (7,'Suzuki','Alto',2012)",
			executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM vehicles WHERE  id=7"
			,executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateVehicleById(){
		Vehicles vehicles = new Vehicles("Suzuki","Swift",2015);
		restTemplate.put(baseUrl + "/{id}",vehicles, 7);
		Vehicles vehicleFromDb =  h2Repository.findById(7).get();
		assertAll(
				()->assertNotNull(vehicleFromDb),
				()->assertEquals("Swift",vehicleFromDb.getModel()),
				()->assertEquals(2015,vehicleFromDb.getYear())
		);
	}

	@Test
	@Sql(statements = "INSERT INTO vehicles (id,brand,model,vehicle_year) VALUES (8,'Tesla','ModelX',2022)",
			executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void testDeleteVehicleById(){
		int recordCount = h2Repository.findAll().size();
		assertEquals(1, recordCount);
		restTemplate.delete(baseUrl+"/{id}",8);
		assertEquals(0,h2Repository.findAll().size());
	}

}
