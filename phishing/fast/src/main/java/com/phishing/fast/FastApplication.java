package com.phishing.fast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EntityScan(basePackages = "com.phishing.fast")
public class FastApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastApplication.class, args);
	}

}
