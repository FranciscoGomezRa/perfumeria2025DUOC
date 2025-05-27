package com.gsf.msvc_pedido.service;

import com.gsf.msvc_pedido.dtos.PedidoCompletoDTO;
import com.gsf.msvc_pedido.dtos.idClienteDTO;
import com.gsf.msvc_pedido.model.entity.Pedido;

import java.util.List;

public interface PedidoService {
    Pedido findById(long id);
    List<Pedido> findAll();
    List<Pedido> findByClienteId(idClienteDTO idclientedto);
    Pedido save(Pedido pedido);
    void deleteById(Long id);
    PedidoCompletoDTO emisionTotalPedidos(long id);
}
