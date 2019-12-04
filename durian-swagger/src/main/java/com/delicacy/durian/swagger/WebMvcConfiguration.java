package com.delicacy.durian.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;

@Configuration
@ConditionalOnClass(SpringfoxWebMvcConfiguration.class)
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		UserRoleAuthorizationInterceptor interceptor = new UserRoleAuthorizationInterceptor();
		interceptor.setAuthorizedRoles("admin");
		registry.addInterceptor(interceptor).excludePathPatterns("/webjars/**",
				"/swagger/**",
				"/v2/api-docs",
				"/doc.html",
				"/swagger-ui.html",
				"/swagger-resources/**");
	}
}