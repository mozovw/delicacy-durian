package com.delicacy.durian.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.delicacy.durian.oauth.mapper")
@SpringBootApplication
public class DurianOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(DurianOauthApplication.class, args);
	}

}
