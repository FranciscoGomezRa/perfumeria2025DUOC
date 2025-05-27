package com.gsf.msvc_pedido.service;

import com.gsf.msvc_pedido.clients.ClienteClientRest;
import com.gsf.msvc_pedido.clients.DetallePedidoClientRest;
import com.gsf.msvc_pedido.clients.SucursalClientRest;
import com.gsf.msvc_pedido.dtos.PedidoCompletoDTO;
import com.gsf.msvc_pedido.dtos.idClienteDTO;
import com.gsf.msvc_pedido.dtos.idPedidoDTO;
import com.gsf.msvc_pedido.exception.PedidoException;
import com.gsf.msvc_pedido.model.Cliente;
import com.gsf.msvc_pedido.model.DetallePedido;
import com.gsf.msvc_pedido.model.entity.Pedido;
import com.gsf.msvc_pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public PedidoCompletoDTO emisionTotalPedidos(long id) {

        Pedido pedido = this.pedidoRepository.findById(id).orElseThrow(
                () -> new PedidoException("El Pedido no existe en la base de datos")
        );

        PedidoCompletoDTO pedidocompleto = new PedidoCompletoDTO();
        Cliente cliente = this.clienteClientRest.findById(pedido.getIdCliente()).getBody();

        idPedidoDTO pedidodto = new idPedidoDTO();
        pedidodto.setIdPedido(id);

        List<DetallePedido> listaDetallePedidos = this.detallePedidoClientRest.BuscadorPorIdPedido(pedidodto).getBody();

        pedidocompleto.setIdPedido(id);
        pedidocompleto.setNombreCliente(cliente.getNombreCliente());
        pedidocompleto.setListaDetallePedidos(listaDetallePedidos);

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

    @Override
    public Pedido save(Pedido pedido) {


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
}
