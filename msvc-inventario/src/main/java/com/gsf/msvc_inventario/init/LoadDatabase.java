package com.gsf.msvc_inventario.init;

import com.gsf.msvc_inventario.client.ProductClientRest;
import com.gsf.msvc_inventario.client.SucursalClientRest;
import com.gsf.msvc_inventario.model.Producto;
import com.gsf.msvc_inventario.model.Sucursal;
import com.gsf.msvc_inventario.model.entity.Inventario;
import com.gsf.msvc_inventario.repository.InventoryRepository;
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
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductClientRest productClientRest;
    @Autowired
    private SucursalClientRest sucursalClientRest;

    @Override
    public void run(String... args) throws Exception {
        /*Activo el Faker(Generador de INFO para la BDD)
        * Necesitas cargarlo en el POM
        * */
        Faker faker = new Faker(Locale.of("es","CL"));

        if(inventoryRepository.count()==0){
            for(int i=0;i<100;i++){
                Inventario inventario = new Inventario();
                Long idPrueba = (long) i+1;
                Producto producto = productClientRest.findById(idPrueba).getBody();
                Sucursal sucursal = sucursalClientRest.findById(idPrueba).getBody();
                assert producto != null;
                assert sucursal != null;

                Integer cantidad = 5;

                inventario.setCantidadInventario(cantidad);
                inventario.setIdProducto(producto.getIdProducto());
                inventario.setIdSucursal(sucursal.getIdSucursal());
                inventario = inventoryRepository.save(inventario);

                log.info("El Inventario creado es {}", inventario);

            }
        }

    }
}
