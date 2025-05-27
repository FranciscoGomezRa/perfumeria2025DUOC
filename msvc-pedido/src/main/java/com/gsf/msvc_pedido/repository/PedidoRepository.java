package com.gsf.msvc_pedido.repository;

import com.gsf.msvc_pedido.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {


    List<Pedido> findByIdCliente(Long idCliente);

}
