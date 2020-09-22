package com.cma.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	// Access Swagger: http://localhost:8080/swagger-ui/index.html#/

	@Bean
	public Docket customAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.cma.controller"))
					.paths(PathSelectors.regex("/api.*"))
					.build()
					.apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfo("Customer Manager - Angular & Spring Boot",
				"REST API for Customer Management", "1.0", "Terms of service",
				new Contact("Barun Singh", "https://www.linkedin.com/in/barun-singh-a5486b144/",
						"barun.singh1204@gmail.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());		
	}

}