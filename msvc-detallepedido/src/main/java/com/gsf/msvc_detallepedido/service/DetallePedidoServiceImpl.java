package com.gsf.msvc_detallepedido.service;

import com.gsf.msvc_detallepedido.clients.InventarioClientRest;
import com.gsf.msvc_detallepedido.clients.PedidoClientRest;
import com.gsf.msvc_detallepedido.clients.ProductoClientRest;
import com.gsf.msvc_detallepedido.clients.SucursalClientRest;
import com.gsf.msvc_detallepedido.dtos.idPedidoDTO;
import com.gsf.msvc_detallepedido.exceptions.DetallePedidoException;
import com.gsf.msvc_detallepedido.model.Pedido;
import com.gsf.msvc_detallepedido.model.Producto;
import com.gsf.msvc_detallepedido.model.entity.DetallePedido;
import com.gsf.msvc_detallepedido.repository.DetallePedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService{

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ProductoClientRest productoClientRest;

    @Autowired
    private SucursalClientRest sucursalClientRest;
    @Autowired
    private InventarioClientRest inventarioClientRest;

    @Autowired
    private PedidoClientRest pedidoClientRest;


    @Override
    public DetallePedido findById(Long id) {
        return this.detallePedidoRepository.findById(id).orElseThrow(
                () -> new DetallePedidoException("El Detalle Pedido no existe en la base de datos")
        );
    }

    @Override
    public List<DetallePedido> findAll() {
        return this.detallePedidoRepository.findAll();
    }

    @Override
    public DetallePedido save(DetallePedido detallePedido) {
        Pedido pedido = this.pedidoClientRest.findById(detallePedido.getIdPedido()).getBody();
        Producto producto = this.productoClientRest.findById(detallePedido.getIdProducto()).getBody();

        detallePedido.setTotal(producto.getPrecioProducto()*detallePedido.getCantidad());

        return this.detallePedidoRepository.save(detallePedido);
    }
    @Override
    public void deleteById(Long id) {
        if (this.detallePedidoRepository.findById(id).isPresent()) {
            this.detallePedidoRepository.deleteById(id);
        } else {
            throw new DetallePedidoException("No se encuentra el DetallePedido con:" + id);
        }
    }


    @Override
    public List<DetallePedido> BuscadorPorIdPedido(idPedidoDTO idPedidoDTO) {
        return this.detallePedidoRepository.findByIdPedido(idPedidoDTO.getIdPedido());
    }

}
