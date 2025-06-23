package com.gsf.msvc_pedido.init;


import com.gsf.msvc_pedido.clients.ClienteClientRest;
import com.gsf.msvc_pedido.clients.SucursalClientRest;
import com.gsf.msvc_pedido.dtos.idPedidoDTO;
import com.gsf.msvc_pedido.model.Cliente;
import com.gsf.msvc_pedido.model.Sucursal;
import com.gsf.msvc_pedido.model.entity.Pedido;
import com.gsf.msvc_pedido.repository.PedidoRepository;
import com.gsf.msvc_pedido.service.PedidoServiceImpl;
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
    private ClienteClientRest clienteClientRest;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private SucursalClientRest sucursalClientRest;
    @Autowired
    private PedidoServiceImpl pedidoServiceImpl;


    @Override
    public void run(String... args) throws Exception {
        /*Faker faker = new Faker(Locale.of("es","CL"));*/

        if(pedidoRepository.count() == 0){
            for(int i=0;i<100;i++){
                Long idPrueba = (long) i+1;

                Pedido pedido = new Pedido();

                Cliente cliente = this.clienteClientRest.findById(idPrueba).getBody();
                Sucursal sucursal = this.sucursalClientRest.findById(idPrueba).getBody();

                assert sucursal != null;
                pedido.setIdSucursal(sucursal.getIdSucursal());
                assert cliente != null;
                pedido.setIdCliente(cliente.getIdCliente());

                pedidoRepository.save(pedido);
                log.info("El producto asociado a Detalle Pedido es {}", pedido);
            }

        }

    }
}
