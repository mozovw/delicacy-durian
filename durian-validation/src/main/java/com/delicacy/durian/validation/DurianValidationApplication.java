package com.delicacy.durian.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.delicacy")
public class DurianValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DurianValidationApplication.class, args);
    }

}
