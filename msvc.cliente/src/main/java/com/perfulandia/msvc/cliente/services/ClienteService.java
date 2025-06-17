package com.perfulandia.msvc.cliente.services;

import com.gsf.msvc_productos.dtos.ProductoUpdateDTO;
import com.gsf.msvc_productos.models.Producto;
import com.perfulandia.msvc.cliente.dtos.ClienteUpdateDTO;
import com.perfulandia.msvc.cliente.models.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);
    Cliente update(Long idCliente, ClienteUpdateDTO clienteUpdateDTO);