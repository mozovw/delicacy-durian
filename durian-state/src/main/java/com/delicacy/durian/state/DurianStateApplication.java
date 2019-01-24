package com.delicacy.durian.state;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DurianStateApplication {

	public static void main(String[] args) {
		//args = new String[]{"--debug"};
		SpringApplication.run(DurianStateApplication.class, args);
	}
}
