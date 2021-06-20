package com.gabrielfmagalhaes.payments.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("com.gabrielfmagalhaes.payments"))
	        .paths(PathSelectors.any())
	        .build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
            .title("Gabriel Ferreira Magalhães")
            .description("API's for accounts transactions")
			.version("1.0").build();
	}
}
