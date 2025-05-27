package com.gsf.msvc_detallepedido.service;

import com.gsf.msvc_detallepedido.clients.InventarioClientRest;
import com.gsf.msvc_detallepedido.clients.ProductoClientRest;
import com.gsf.msvc_detallepedido.clients.SucursalClientRest;
import com.gsf.msvc_detallepedido.exceptions.DetallePedidoException;
import com.gsf.msvc_detallepedido.model.entity.DetallePedido;
import com.gsf.msvc_detallepedido.repository.DetallePedidoRepository;
import org.hibernate.sql.Delete;

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

        return this.detallePedidoRepository.save(detallePedido);
    }

    @Override
    public Delete delete(DetallePedido detallePedido) {
        return null;
    }
}
