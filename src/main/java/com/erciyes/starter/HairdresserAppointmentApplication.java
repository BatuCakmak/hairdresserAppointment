package com.erciyes.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.erciyes"})
@EnableJpaRepositories(basePackages = {"com.erciyes"})
@ComponentScan(basePackages = {"com.erciyes"})
public class HairdresserAppointmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(HairdresserAppointmentApplication.class, args);
	}

}
