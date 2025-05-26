package com.gsf.msvc_pedido.service;

import com.gsf.msvc_pedido.clients.ClienteClientRest;
import com.gsf.msvc_pedido.clients.SucursalClientRest;
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
    private ClienteClientRest clienteClientRest;

    @Autowired
    private SucursalClientRest sucursalClientRest;

    @Override
    public Pedido findById(long id) {
        return null;
    }

    @Override
    public List<Pedido> findAll() {
        return List.of();
    }

    @Override
    public List<Pedido> findByClienteId(long id) {
        return List.of();
    }

    @Override
    public Pedido save(Pedido pedido) {
        return null;
    }

    @Override
    public void delete(Pedido pedido) {

    }
}
