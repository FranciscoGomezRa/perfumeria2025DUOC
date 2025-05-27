package com.gsf.msvc_detallepedido.service;


import com.gsf.msvc_detallepedido.model.entity.DetallePedido;
import org.hibernate.sql.Delete;

import java.util.List;

public interface DetallePedidoService {

    DetallePedido findById(Long id);
    List<DetallePedido> findAll();
    DetallePedido save(DetallePedido detallePedido);
    Delete delete(DetallePedido detallePedido);
}
