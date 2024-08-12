package com.tron.FoodHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan
@ComponentScan
@SpringBootApplication
public class FoodHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodHubApplication.class, args);
	}

}
