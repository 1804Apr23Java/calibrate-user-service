package com.revature;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public  Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.revature"))
                                .build()
                                .apiInfo(metaInfo());
	}
	
    private ApiInfo metaInfo() {
    	
    	Collection<VendorExtension> vendorExtensions = new ArrayList<>();

        ApiInfo apiInfo = new ApiInfo(
                "Calibrate Account Service REST API",
                "REST API for account microservice that allows users to create an account, "
                + "get an account by Id, get all accounts, login with a valid account and logout",
                "1.0",
                "Terms of Service",
                new Contact("Revature", "https://revature.com/",
                        "info@revature.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", vendorExtensions
        );

        return apiInfo;
    }
	
}
