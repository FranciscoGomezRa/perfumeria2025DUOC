package com.perfulandia.msvc.cliente.services;

import com.perfulandia.msvc.cliente.models.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);

}
