package com.delicacy.durian.oauth.sso.client1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;


@SpringBootApplication
public class DurianOauthSsoClient1Application {

	public static void main(String[] args) {
		SpringApplication.run(DurianOauthSsoClient1Application.class, args);
	}

}
