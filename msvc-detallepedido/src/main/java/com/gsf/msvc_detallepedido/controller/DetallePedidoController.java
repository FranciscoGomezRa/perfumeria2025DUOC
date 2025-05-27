package com.gsf.msvc_detallepedido.controller;



import com.gsf.msvc_detallepedido.dtos.idPedidoDTO;
import com.gsf.msvc_detallepedido.model.entity.DetallePedido;
import com.gsf.msvc_detallepedido.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/detallepedido")
@Validated

public class DetallePedidoController {


    @Autowired
    private DetallePedidoService detallePedidoService;


    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.detallePedidoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DetallePedido>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.detallePedidoService.findAll());
    }

    @PostMapping
    public ResponseEntity<DetallePedido> save(DetallePedido detallePedido) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.detallePedidoService.save(detallePedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        detallePedidoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Producto Eliminado");
    }


    @PostMapping("/pedido")
    public ResponseEntity<List<DetallePedido>> BuscadorPorIdPedido(idPedidoDTO idPedidoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.detallePedidoService.BuscadorPorIdPedido(idPedidoDTO));
    }


}
