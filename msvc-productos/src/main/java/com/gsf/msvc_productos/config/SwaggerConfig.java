package com.gsf.msvc_productos.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        Contact contact =new Contact();
        contact.setName("Francisco Gómez");
        contact.setEmail("FrancxD@xDDD.com");


        return new OpenAPI()
                .info(new Info()
                        .title("API - MSVC - PRODUCTOS")
                        .version("1.0.0")
                        .description("Este es el MicroServicio de Productos, Realiza consultas CRUD")
                        .contact(contact)
                        .termsOfService("No colapsar la API")
                        .summary("Esta es una API dentro de un proyecto de MSVC")


                );
    }
}
