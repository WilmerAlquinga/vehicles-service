package dev.wsalquinga.vehicles.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author wsalquinga on 30/11/2023
 */
@Configuration
public class OpenAPIConfig {
    @Value("${vehicles.openapi.local-url}")
    private String localUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(localUrl);
        devServer.setDescription("Server URL in Local environment");

        Contact contact = new Contact();
        contact.setEmail("wilmer.alquinga21@gmail.com");
        contact.setName("Wilmer Alquinga");
        contact.setUrl("");

        License mitLicense = new License().name("Apache License 2.0").url("https://choosealicense.com/licenses/apache-2.0/");

        Info info = new Info()
                .title("Demo vehicles Service API")
                .version("1.0.0")
                .contact(contact)
                .description("This API exposes endpoints to search vehicles.").termsOfService("")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
