package com.esmael.taskmanager.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.esmael.taskmanager.entity.Role;
import com.esmael.taskmanager.entity.User;
import com.esmael.taskmanager.repository.RoleRepository;
import com.esmael.taskmanager.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	

	public DataInitializer(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public void run(String... args) throws Exception {
		
		//create roles if missing
		Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseGet(()->
		
				roleRepository.save(new Role(null, "ROLE_ADMIN")));
		
		//create role_user if missing
		Role useRole = roleRepository.findByName("ROLE_USER").orElseGet(()-> roleRepository.save(new Role(null, "ROLE_USER")));
		
		//CREATE ADMIN USER IF MISSING
		if(!userRepository.existsByEmail("admin@test.com")) {
			
			User admin = new User();
			
			admin.setUsername("admin");
			admin.setEmail("admin@test.com");
			
			admin.setPassword(passwordEncoder.encode("test"));
			
			admin.setRoles(Set.of(adminRole, useRole));
			
			userRepository.save(admin);
			
			System.out.println("Default admin user created");
			
		}
	
	}




		

	

}
