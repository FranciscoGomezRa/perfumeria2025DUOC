package com.gsf.msvc_detallepedido.config;



import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        Contact contact =new Contact();
        contact.setName("Gonzalo Pines");
        contact.setEmail("Labestia666@xDDD.com");


        Schema<?> hiddenSchema = new Schema<>();
        hiddenSchema.addExtension("hidden", true);

        return new OpenAPI()
                .info(new Info()
                        .title("API - MSVC - Detalle Pedido")
                        .version("1.0.0")
                        .description("Este es el MicroServicio de Detalle Pedido, Realiza consultas CRUD")
                        .contact(contact)
                        .termsOfService("Hola muchachos")
                        .summary("Esta es una API dentro de un proyecto de MSVC")



                ).components(new Components()
                        .addSchemas("InventoryUpdateDTO", hiddenSchema));
    }
}
