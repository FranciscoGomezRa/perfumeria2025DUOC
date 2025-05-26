package com.gsf.msvc_pedido.clients;

import com.gsf.msvc_pedido.model.Cliente;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ClienteClientRest {

    @GetMapping
    ResponseEntity<List<Cliente>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<Cliente>findById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Cliente> save(@Valid @RequestBody Cliente cliente);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

}
