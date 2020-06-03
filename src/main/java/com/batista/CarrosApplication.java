package com.batista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CarrosApplication {

	public static void main(String[] args) {
	
		BCryptPasswordEncoder bc = new 	BCryptPasswordEncoder();
		System.out.println(bc.encode("123"));
		SpringApplication.run(CarrosApplication.class, args);
	}

}
