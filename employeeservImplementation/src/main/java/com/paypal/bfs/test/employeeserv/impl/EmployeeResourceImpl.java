package com.paypal.bfs.test.employeeserv.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.dao.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.entity.AddressDTO;
import com.paypal.bfs.test.entity.EmployeeDTO;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeResourceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

    @Override
    public ResponseEntity<Employee> employeeGetById(Integer id) {
    	EmployeeDTO employeeDTO = employeeRepository.findById(id).orElse(null);
    	if(employeeDTO == null) {
    		return ResponseEntity.notFound().build();
    	}   	
        Employee employee = new Employee();
        Address address = new Address();
        BeanUtils.copyProperties(employeeDTO.getAddress(), address);
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setAddress(address);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<Employee> addEmployee(Employee employee) {
		AddressDTO addressDTO = new AddressDTO();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employee.getAddress(), addressDTO);
		BeanUtils.copyProperties(employee, employeeDTO, "id");
		employeeDTO.setAddress(addressDTO);
		EmployeeDTO savedEmployee = employeeRepository.save(employeeDTO);
		employee.setId(savedEmployee.getId());
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}
}
