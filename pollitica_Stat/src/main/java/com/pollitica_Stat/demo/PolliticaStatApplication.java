package com.pollitica_Stat.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.pollitica_Stat")
@EnableJpaRepositories(basePackages = "com.pollitica_Stat.Repository")
@EntityScan(basePackages = "com.pollitica_Stat.Model")
public class PolliticaStatApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolliticaStatApplication.class, args);
	}

}
