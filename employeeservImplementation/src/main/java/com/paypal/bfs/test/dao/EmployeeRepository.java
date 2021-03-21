package com.paypal.bfs.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.bfs.test.entity.EmployeeDTO;

public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Integer>{

}
