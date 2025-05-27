package com.gsf.msvc_detallepedido.clients;


import com.gsf.msvc_detallepedido.dtos.PedidoCompletoDTO;
import com.gsf.msvc_detallepedido.dtos.PedidoDTO;
import com.gsf.msvc_detallepedido.dtos.idClienteDTO;
import com.gsf.msvc_detallepedido.dtos.idPedidoDTO;
import com.gsf.msvc_detallepedido.model.Pedido;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="msvc-pedido",url="localhost:8087/api/v1/pedido")
public interface PedidoClientRest {

    @GetMapping("/{id}")
    ResponseEntity<Pedido> findById(@PathVariable long id);

    @PostMapping("/boleta")
    ResponseEntity<PedidoCompletoDTO> emisionTotalPedidos(@RequestBody@Valid idPedidoDTO idpedidodto);


    @GetMapping
    ResponseEntity<List<Pedido>> findAll();

    @PostMapping
    ResponseEntity<Pedido> save(@Valid @RequestBody PedidoDTO pedidodto);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteById(@PathVariable Long id);
    @PostMapping
    List<Pedido> findByClienteId(idClienteDTO idclientedto);



}
