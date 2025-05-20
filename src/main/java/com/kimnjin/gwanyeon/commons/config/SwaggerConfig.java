package com.kimnjin.gwanyeon.commons.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("GwanYeon API")
            .version("0.0.1-SNAPSHOT")
            .description("GwanYeon API 문서"))
        .components(new Components().addSecuritySchemes("Bearer",
            new SecurityScheme()
                .name("Bearer")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")))
        .addSecurityItem(new SecurityRequirement().addList("Bearer"))
        .servers(List.of(
            new Server().url("").description("Current server")));
  }
}

