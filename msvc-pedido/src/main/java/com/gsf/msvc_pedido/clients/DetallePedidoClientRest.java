package com.gsf.msvc_pedido.clients;

import com.gsf.msvc_pedido.dtos.idPedidoDTO;
import com.gsf.msvc_pedido.model.DetallePedido;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="msvc-detallepedido",url="localhost:8088/api/v1/detallepedido")
public interface DetallePedidoClientRest {

    @GetMapping("/{id}")
    ResponseEntity<DetallePedido> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<DetallePedido>> findAll();

    @PostMapping
    ResponseEntity<DetallePedido> save(@RequestBody @Valid DetallePedido detallePedido);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteById(@PathVariable Long id);

    @PostMapping("/pedido")
    ResponseEntity<List<DetallePedido>> BuscadorPorIdPedido(@RequestBody@Valid idPedidoDTO idpedidodto);



}
