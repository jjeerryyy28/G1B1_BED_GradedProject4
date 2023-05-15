package com.greatlearning.employeeManagementSystem.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.greatlearning.employeeManagementSystem.entity.Employee;
import com.greatlearning.employeeManagementSystem.entity.Role;
import com.greatlearning.employeeManagementSystem.entity.User;
import com.greatlearning.employeeManagementSystem.repository.EmployeeRepository;
import com.greatlearning.employeeManagementSystem.repository.UserJpaRepository;
import com.greatlearning.employeeManagementSystem.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	UserJpaRepository userRepository;

	@Override
	public String addSingleEmployee(Employee employee) {
		repository.save(employee);
		repository.flush();
		return "Employee Added..";
	}

	@Override
	public String addAllEmployees(List<Employee> employees) {
		repository.saveAll(employees);
		repository.flush();
		return "All Employees Added";
	}

	@Override
	public List<Employee> getAllEmployees() {

		return repository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<Employee> SortById(Direction direction) {
		return repository.findAll(Sort.by(direction, "id"));
	}

	@Override
	public String deleteEmployeeById(Integer id) {

		repository.deleteById(id);
		return "Deleted Employee id - " + id;
	}

	@Override
	public String addUser(User user) {
		userRepository.save(user);
		return "user added";
	}

	@Override
	public String addRole(Role role) {
		User addRole = new User();
		addRole.addRole(role);
		return "role added";
	}

	@Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	@Override
	public List<Employee> SortedByName(Direction direction) {
		return repository.findAll(Sort.by(direction, "firstName"));

	}

	@Override
	public List<Employee> searchBy(String name) {
		List<Employee> employee = repository.findByfirstNameContainsAllIgnoreCase(name);
		return employee;
	}

}
