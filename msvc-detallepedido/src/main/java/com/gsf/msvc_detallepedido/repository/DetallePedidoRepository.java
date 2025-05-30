package com.gsf.msvc_detallepedido.repository;

import com.gsf.msvc_detallepedido.model.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    List<DetallePedido> findByIdPedido(Long pedidoId);
    //List<DetallePedido> findByIdCliente(Long clienteId);
}
