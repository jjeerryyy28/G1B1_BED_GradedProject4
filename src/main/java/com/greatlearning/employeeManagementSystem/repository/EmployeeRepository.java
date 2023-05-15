package com.greatlearning.employeeManagementSystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employeeManagementSystem.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByfirstNameContainsAllIgnoreCase(String firstName);

}
