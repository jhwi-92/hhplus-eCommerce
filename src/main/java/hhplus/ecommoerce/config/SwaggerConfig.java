package hhplus.ecommoerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
            .title("hhplus-eCommerce")
            .version("1.0")
            .description("API");

        Server server = new Server();
        server.setUrl("/");

        return new OpenAPI()
            .info(info)
            .servers(List.of(server));
    }
}