package com.perfulandia.msvc.cliente.config;

import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        Contact contact = new Contact();
        contact.setName("Simon Villar");
        contact.setEmail("sim.villar@duocuc.cl");
        return new OpenAPI()
                .info(new Info()
                .title("API - MSVC - Clientes")
                .version("1.0.0")
                .description("Este es el microservicio de clientes, con el puedes realizar todas las consultas"+
                        "CRUD que necesites")
                .contact(contact)
                .termsOfService("NO COLAPSAR LA API")
                .summary("esto es una API dentro de un proyecto de MSVC")
        );
    }


}
