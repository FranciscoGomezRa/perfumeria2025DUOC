package com.gsf.msvc_detallepedido.clients;

import com.gsf.msvc_detallepedido.model.Sucursal;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="msvc-sucursal",url="localhost:8083/api/v1/sucursal")
public interface SucursalClientRest {

    @GetMapping
    ResponseEntity<List<Sucursal>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<Sucursal>findById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Sucursal> save(@Valid @RequestBody Sucursal sucursal);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
