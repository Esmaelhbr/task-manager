package com.esmael.taskmanager.config;



import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()

        .info(new Info()

                .title("Task Manager API")

                .version("1.0")

                .description(
                    "Spring Boot Task Manager API "
                    + "with JWT authentication"
                )
            )
        .addSecurityItem(
                new SecurityRequirement()
                .addList("bearerAuth")
)

.components(
        new io.swagger.v3.oas.models.Components()

                .addSecuritySchemes(
                        "bearerAuth",

                        new SecurityScheme()

                                .name("bearerAuth")

                                .type(SecurityScheme.Type.HTTP)

                                .scheme("bearer")

                                .bearerFormat("JWT")
                )
);
		
	}

}
