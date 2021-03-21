package com.paypal.bfs.test.employeeserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.paypal.bfs.test.exception.handler.CustomizedResponseEntityExceptionHandler;

@SpringBootApplication
@EnableJpaRepositories("com.paypal.bfs.test.dao")
@EntityScan("com.paypal.bfs.test.entity")
@Import(CustomizedResponseEntityExceptionHandler.class)
public class EmployeeservApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeservApplication.class, args);
    }
}