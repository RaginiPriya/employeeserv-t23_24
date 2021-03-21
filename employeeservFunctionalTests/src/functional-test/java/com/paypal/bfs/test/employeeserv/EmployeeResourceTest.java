package com.paypal.bfs.test.employeeserv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EmployeeservApplication.class)
public class EmployeeResourceTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldSaveEmployee() throws JsonProcessingException {
		
		Employee employee = getEmployee();
		ResponseEntity<Employee> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/v1/bfs/employees", employee, Employee.class);

		assertEquals(201, responseEntity.getStatusCodeValue());
		assertTrue(1 == responseEntity.getBody().getId());

		Employee savedEmployee = this.restTemplate.getForObject("http://localhost:" + port + "/v1/bfs/employees/1",
				Employee.class);

		assertEquals("Ragini", savedEmployee.getFirstName());

	}
	
	@Test
	public void invalidEmployee() throws JsonProcessingException {
		
		Employee employee = getEmployee();
		employee.setAddress(null);
		ResponseEntity<Employee> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/v1/bfs/employees", employee, Employee.class);

		assertEquals(400, responseEntity.getStatusCodeValue());

	}
	
	public Employee getEmployee() {
		Address address = new Address();
		address.setLine1("address1");
		address.setCity("chennai");
		address.setState("TN");
		address.setCountry("IN");
		address.setZipCode("600018");
		Employee employee = new Employee();
		employee.setAddress(address);
		employee.setFirstName("Ragini");
		employee.setLastName("Priya");
		employee.setDateOfBirth(LocalDate.of(1995, 11, 20));
		return employee;
	}

}
