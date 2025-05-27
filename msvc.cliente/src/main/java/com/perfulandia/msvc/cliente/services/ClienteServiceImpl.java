package com.perfulandia.msvc.cliente.services;

import com.perfulandia.msvc.cliente.exceptions.ClienteException;
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
        return this.clienteRepository.findAll();
    }

    @Transactional
    @Override
    public Cliente findById(Long id) {
        return this.clienteRepository.findById(id).orElseThrow(
                () -> new ClienteException("El cliente con el id " + id + " no se encuentra registrado.")
        );
    }

    @Transactional
    @Override
    public Cliente save(Cliente cliente) {
        if(this.clienteRepository.findByRut(cliente.getRut()).isPresent()) {
            throw new ClienteException("El cliente con el rut " + cliente.getRut() + " ya existe en la base de datos.");
        }
        return this.clienteRepository.save(cliente);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        this.clienteRepository.deleteById(id);
    }
}
