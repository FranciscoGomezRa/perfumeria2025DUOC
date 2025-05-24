package com.perfulandia.msvc.cliente.repositories;


import com.perfulandia.msvc.cliente.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
