package com.example.lead_manager_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories
@SpringBootApplication
public class LeadManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeadManagerApiApplication.class, args);
	}

}
