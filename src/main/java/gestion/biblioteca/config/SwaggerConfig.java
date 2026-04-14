package gestion.biblioteca.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI soporteApiConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Evaluación Final")
                        .description("Gestión de Biblioteca")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Grupo 6")
                                .email("iv73370044@idat.pe")
                                .url("https://github.com/fernandoruiz2016/API-Biblioteca-SpringBoot")
                        )
                        .license(new License()
                                .name("Proyectos IDAT")
                                .url("https://github.com/fernandoruiz2016/API-Biblioteca-SpringBoot")
                        )
                );
    }
}

