package com.perfulandia.msvc.cliente.init;

import com.perfulandia.msvc.cliente.models.Cliente;
import com.perfulandia.msvc.cliente.repositories.ClienteRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Profile("dev")
@Component
public class LoadDatabase implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));

        if(clienteRepository.count() == 0){
            for(int i=0;i<100;i++){
                Cliente cliente = new Cliente();

                // Generar un RUT chileno básico (formato simple)
                String rut = String.valueOf(faker.number().numberBetween(5_000_000, 25_000_000)) + "-" +
                        faker.number().numberBetween(0, 9);

                cliente.setNombreCliente((faker.name().fullName()));
                cliente.setEmailCliente(faker.internet().emailAddress());
                cliente.setRut(rut);

                cliente = clienteRepository.save(cliente);
                log.info("El cliente creado es {}", cliente);
            }
        }
    }
}
