package com.perfulandia.msvc.cliente.services;

import com.perfulandia.msvc.cliente.dtos.ClienteUpdateDTO;
import com.perfulandia.msvc.cliente.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);
    Cliente update(Long idCliente, ClienteUpdateDTO clienteUpdateDTO);