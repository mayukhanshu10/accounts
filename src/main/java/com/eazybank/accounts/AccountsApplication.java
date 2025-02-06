package com.eazybank.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



/*
	For now our Main class is inside package com.eazybank.accounts and other packages are subpackage of this Main package.
	If for example we create Controller package or Entity Package outside this package then we have to put
	following Annotations here:

	@ComponentScans({@ComponentScan("com.eazybytes.accounts.controller")})
	@EnableJpaRepositories("com.eazybytes.accounts.repository)
	@EntityScan("com.eazybytes.accounts.model")
 */
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Microservice REST API Documentation",
				description = "EazyBank Accounts Microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Mayukh Srivastava",
						email = "mayukhsri10@gmail.com"
				),
				license = @License(
						name = "Some License Name",
						url = "xyz.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Tell more about our APIs and services",
				url = "xyz.com"
		)
)
@EnableFeignClients
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
