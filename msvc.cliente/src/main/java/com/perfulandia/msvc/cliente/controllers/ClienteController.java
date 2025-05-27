package com.perfulandia.msvc.cliente.controllers;


import com.perfulandia.msvc.cliente.models.Cliente;
import com.perfulandia.msvc.cliente.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Validated

public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente>findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.save(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.clienteService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
