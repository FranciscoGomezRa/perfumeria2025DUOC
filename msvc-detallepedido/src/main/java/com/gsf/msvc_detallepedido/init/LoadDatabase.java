package com.gsf.msvc_detallepedido.init;



import com.gsf.msvc_detallepedido.clients.PedidoClientRest;
import com.gsf.msvc_detallepedido.clients.ProductoClientRest;
import com.gsf.msvc_detallepedido.model.Pedido;
import com.gsf.msvc_detallepedido.model.Producto;
import com.gsf.msvc_detallepedido.model.entity.DetallePedido;
import com.gsf.msvc_detallepedido.repository.DetallePedidoRepository;
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
    private DetallePedidoRepository detallePedidoRepository;
    @Autowired
    private PedidoClientRest pedidoClientRest;
    @Autowired
    private ProductoClientRest productoClientRest;


    @Override
    public void run(String... args) throws Exception {
        /*Faker faker = new Faker(Locale.of("es","CL"));*/

        if(detallePedidoRepository.count() == 0){
            for(int i=0;i<100;i++){
                Long idPrueba = (long) i+1;

                DetallePedido detallePedido = new DetallePedido();

                Pedido pedido = this.pedidoClientRest.findById(idPrueba).getBody();
                Producto producto = this.productoClientRest.findById(idPrueba).getBody();

                detallePedido.setIdPedido(pedido.getIdPedido());
                detallePedido.setIdProducto(producto.getIdProducto());
                detallePedido.setCantidad(5);
                detallePedido.setTotal(producto.getPrecioProducto()*detallePedido.getCantidad());

                /*Este Metodo incluye el checkeo de Stock asociado al inventario, pedido trae una Sucursal*/
                detallePedidoRepository.save(detallePedido);

                log.info("El producto asociado a Detalle Pedido es {}", detallePedido);
            }
        }

    }
}
