package com.gsf.msvc_sucursal.init;


import com.gsf.msvc_sucursal.model.Sucursal;
import com.gsf.msvc_sucursal.repositories.SucursalRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Profile("dev")
@Component
public class LoadDatabase implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    SucursalRepository sucursalRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));

        Set<String> nombresUtilizados= new HashSet<>();
        String nombreSucursal;
        Boolean flag;

        if(sucursalRepository.count() == 0){
            for(int i=0;i<100;i++){
                Sucursal sucursal = new Sucursal();

                flag=false;
                while (!flag) {
                    nombreSucursal = faker.animal().scientificName();
                    if (!nombresUtilizados.contains(sucursal.getNombreSucursal())) {
                        sucursal.setNombreSucursal(nombreSucursal);
                        sucursal.setDireccionSucursal(faker.address().fullAddress());
                        nombresUtilizados.add(sucursal.getNombreSucursal());
                        flag=true;
                    }
                }
                sucursal=sucursalRepository.save(sucursal);
                log.info("El sucursal creada es {}", sucursal);
            }
        }

    }
}
