package app.shopapi.Configurations;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info().title("WebApi")
                        .description("Spring Boot Web Shopping API")
                        .version("v1.0.0")
                        .contact(new Contact().name("Saeed Rajabi").url("https://github.com/ArkaSoftwareTeam").email("arkasoftware1@gmail.com"))
                        .license(new License().name("License").url("/")))
                .externalDocs(new ExternalDocumentation().description("Spring Boot Web App Api Documentation")
                        .url("http://localhost:8080/swagger-ui/index.html"));
    }
}
