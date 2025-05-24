package com.perfulandia.msvc.cliente.services;

import com.perfulandia.msvc.cliente.models.Cliente;
import com.perfulandia.msvc.cliente.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
@Autowired
private ClienteRepository clienteRepository;

    @Transactional
    @Override
    public List<Cliente> findAll() {
        return List.of();
    }

    @Transactional
    @Override
    public Cliente findById(Long id) {
        return this.clienteRepository.findById(id).orElseThrow(
                () -> new Cliente

        );
    }

    @Transactional
    @Override
    public Cliente save(Cliente cliente) {
        return null;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

    }
}
