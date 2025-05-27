package com.gsf.msvc_pedido.controller;


import com.gsf.msvc_pedido.dtos.PedidoCompletoDTO;
import com.gsf.msvc_pedido.dtos.PedidoDTO;
import com.gsf.msvc_pedido.dtos.idClienteDTO;
import com.gsf.msvc_pedido.model.entity.Pedido;
import com.gsf.msvc_pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pedido")
@Validated

public class PedidoController {

    @Autowired
    private PedidoService pedidoService;


    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable long id){
            return ResponseEntity.status(HttpStatus.OK).body(this.pedidoService.findById(id));};


    @PostMapping("/boleta")
    public ResponseEntity<PedidoCompletoDTO> emisionTotalPedidos(long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.pedidoService.emisionTotalPedidos(id));
    }


    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.pedidoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Pedido> save(PedidoDTO pedidodto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService.save(pedidodto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        pedidoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Producto Eliminado");
    }
    @PostMapping("/cliente")
    public List<Pedido> findByClienteId(idClienteDTO idclientedto) {
        return this.pedidoService.findByClienteId(idclientedto);
        }



}




