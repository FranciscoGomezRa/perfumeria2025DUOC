package com.gsf.msvc_pedido.service;

import com.gsf.msvc_pedido.dtos.PedidoCompletoDTO;
import com.gsf.msvc_pedido.dtos.PedidoDTO;
import com.gsf.msvc_pedido.dtos.idClienteDTO;
import com.gsf.msvc_pedido.dtos.idPedidoDTO;
import com.gsf.msvc_pedido.model.entity.Pedido;

import java.util.List;

public interface PedidoService {
    Pedido findById(long id);
    PedidoCompletoDTO emisionPedidoCalculado(Long id);
    List<Pedido> findAll();
    List<Pedido> findByClienteId(idClienteDTO idclientedto);
    Pedido save(PedidoDTO pedidodto);
    void deleteById(Long id);
    PedidoCompletoDTO emisionTotalPedidos(idPedidoDTO pedidodto);
    Pedido update(Long id ,PedidoDTO pedidodto);
}
