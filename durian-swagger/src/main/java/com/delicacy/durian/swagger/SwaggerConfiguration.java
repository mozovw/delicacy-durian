/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.delicacy.durian.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger文档，只有在测试环境才会使用
 * @author LGH
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfiguration {

	 @Bean
	 public Docket createRestApi() {
	     return new Docket(DocumentationType.SWAGGER_2)
	     .apiInfo(apiInfo())
	     .select()
	     .apis(RequestHandlerSelectors.basePackage("com.delicacy"))
	     .paths(PathSelectors.any())
	     .build();
	 }

	/**
	 * http://localhost:8080/doc.html
	 * http://localhost:8080/swagger-ui.html
	 * @return
	 */
	@Bean
	 public ApiInfo apiInfo() {
	     return new ApiInfoBuilder()
	     .title("管理系统接口文档")
	     .description("接口文档Swagger版")
	     .termsOfServiceUrl("https://www.xxx.com/")
	     .contact(new Contact("信息科技有限公司","https://www.xxx.com/", "xx@foxmail.com"))
	     .version("1.0")
	     .build();
	 }
}