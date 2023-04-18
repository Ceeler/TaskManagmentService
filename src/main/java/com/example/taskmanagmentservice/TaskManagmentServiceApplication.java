package com.example.taskmanagmentservice;

import com.example.taskmanagmentservice.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@SpringBootApplication
public class TaskManagmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagmentServiceApplication.class, args);
	}

}
