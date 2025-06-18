package com.gsf.msvc_productos.init;


import com.gsf.msvc_productos.models.Producto;
import com.gsf.msvc_productos.repository.ProductoRepository;
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
    ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));

        if(productoRepository.count() == 0){
            for(int i=0;i<100;i++){
                Producto producto = new Producto();

                String precio = faker.commerce().price();

                producto.setNombreProducto(faker.commerce().productName());
                producto.setDescripcionProducto(faker.commerce().brand());
                producto.setPrecioProducto(Float.parseFloat(precio.replace(",",".")));
                producto = productoRepository.save(producto);
                log.info("El producto creado es {}", producto);
            }
        }

    }


}
