package com.gsf.msvc_pedido.service;

import com.gsf.msvc_pedido.model.entity.Pedido;

import java.util.List;

public interface PedidoService {
    Pedido findById(long id);
    List<Pedido> findAll();
    List<Pedido> findByClienteId(long id);
    Pedido save(Pedido pedido);
    void delete(Pedido pedido);
}
