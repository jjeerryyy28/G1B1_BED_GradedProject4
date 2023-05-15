package com.greatlearning.employeeManagementSystem.bootStrap;

import javax.transaction.Transactional;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.employeeManagementSystem.entity.Role;
import com.greatlearning.employeeManagementSystem.entity.User;
import com.greatlearning.employeeManagementSystem.repository.UserJpaRepository;



@Configuration
public class BootstrapAppData {

	private final UserJpaRepository userJpaRepository;

	private final PasswordEncoder passwordEncoder;

	public BootstrapAppData(UserJpaRepository userJpaRepository,PasswordEncoder passwordEncoder) {
		this.userJpaRepository = userJpaRepository;
		this.passwordEncoder = passwordEncoder;

	}

	@Transactional
	@EventListener(ApplicationReadyEvent.class)
	public void insertAppData(ApplicationReadyEvent event) {

		User testAdmin = new User("admin",this.passwordEncoder.encode("admin"));
		User testUser = new User("user", this.passwordEncoder.encode("user"));

		Role adminRole = new Role("ADMIN");
		Role userRole = new Role("USER");

		testAdmin.addRole(userRole);
		testAdmin.addRole(adminRole);

		testUser.addRole(userRole);

		this.userJpaRepository.save(testAdmin);
		this.userJpaRepository.save(testUser);
		System.out.println("Test data inserted... ");

	}
}
