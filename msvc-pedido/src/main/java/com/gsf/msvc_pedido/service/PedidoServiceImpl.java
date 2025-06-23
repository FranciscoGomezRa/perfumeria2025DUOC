package com.gsf.msvc_pedido.service;

import com.gsf.msvc_pedido.clients.ClienteClientRest;
import com.gsf.msvc_pedido.clients.DetallePedidoClientRest;
import com.gsf.msvc_pedido.clients.SucursalClientRest;
import com.gsf.msvc_pedido.dtos.PedidoCompletoDTO;
import com.gsf.msvc_pedido.dtos.PedidoDTO;
import com.gsf.msvc_pedido.dtos.idClienteDTO;
import com.gsf.msvc_pedido.dtos.idPedidoDTO;
import com.gsf.msvc_pedido.exception.PedidoException;
import com.gsf.msvc_pedido.model.Cliente;
import com.gsf.msvc_pedido.model.DetallePedido;
import com.gsf.msvc_pedido.model.Sucursal;
import com.gsf.msvc_pedido.model.entity.Pedido;
import com.gsf.msvc_pedido.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;


@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoClientRest detallePedidoClientRest;

    @Autowired
    private ClienteClientRest clienteClientRest;

    @Autowired
    private SucursalClientRest sucursalClientRest;

    @Override
    public Pedido findById(long id) {

        return this.pedidoRepository.findById(id).orElseThrow(
                () -> new PedidoException("El Pedido no existe en la base de datos")
        );

    }

    @Transactional
    @Override
    public PedidoCompletoDTO emisionTotalPedidos(idPedidoDTO pedidodto) {

        Pedido pedido = this.pedidoRepository.findById(pedidodto.getIdPedido()).orElseThrow(
                () -> new PedidoException("El Pedido no existe en la base de datos")
        );

        PedidoCompletoDTO pedidocompleto = new PedidoCompletoDTO();
        Cliente cliente = this.clienteClientRest.findById(pedido.getIdCliente()).getBody();


        List<DetallePedido> listaDetallePedidos = this.detallePedidoClientRest.BuscadorPorIdPedido(pedidodto).getBody();
        Double totalPedido = listaDetallePedidos.stream()
                .mapToDouble(DetallePedido::getTotal) // Convierte a DoubleStream
                .sum();

        pedidocompleto.setIdPedido(pedidodto.getIdPedido());
        pedidocompleto.setNombreCliente(cliente.getNombreCliente());

        pedidocompleto.setListaDetallePedidos(listaDetallePedidos);
        pedidocompleto.setTotalPedido(totalPedido);

        return pedidocompleto;


    }


    @Override
    public List<Pedido> findAll() {
        return this.pedidoRepository.findAll();
    }

    @Override
    public List<Pedido> findByClienteId(idClienteDTO idclientedto) {
        Cliente cliente = this.clienteClientRest.findById(idclientedto.getIdCliente()).getBody();
        return this.pedidoRepository.findByIdCliente(idclientedto.getIdCliente());
    }

    @Transactional
    @Override
    public Pedido save(PedidoDTO pedidodto) {
        Sucursal sucursal = this.sucursalClientRest.findById(pedidodto.getIdSucursal()).getBody();
        Cliente cliente = this.clienteClientRest.findById(pedidodto.getIdCliente()).getBody();

        Pedido pedido = new Pedido();
        pedido.setIdSucursal(sucursal.getIdSucursal());
        pedido.setIdCliente(cliente.getIdCliente());

        return this.pedidoRepository.save(pedido);
    }

    @Override
    public void deleteById(Long id) {
        if (this.pedidoRepository.findById(id).isPresent()) {
            this.pedidoRepository.deleteById(id);
        } else {
            throw new PedidoException("No se encuentra el Pedido con:" + id);
        }
    }
    @Override
    public Pedido update(Long id, PedidoDTO pedidodto) {
        Pedido pedido = this.pedidoRepository.findById(id).orElseThrow(
                () -> new PedidoException("El Pedido no existe en la base de datos")
        );
        return this.pedidoRepository.save(pedido);

    }
}
