package com.jedidi.ssd.codescan;

/*
 * Code Made by Faouzi Jedidi
 * S1719017
 *
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CodescanApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodescanApplication.class, args);
	}

}
