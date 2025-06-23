package com.gsf.msvc_pedido.init;


import com.gsf.msvc_pedido.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class LoadDatabase implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));

        if(pedidoRepository.count() == 0){
            for(int i=0;i<100;i++){
                Producto producto = new Producto();

                String precio = faker.commerce().price();

                producto.setNombreProducto(faker.commerce().productName());
                producto.setDescripcionProducto(faker.commerce().brand());
                producto.setPrecioProducto(Float.parseFloat(precio.replace(",",".")));
                producto = pedidoRepository.save(producto);
                log.info("El producto creado es {}", producto);
            }
        }

    }


}
