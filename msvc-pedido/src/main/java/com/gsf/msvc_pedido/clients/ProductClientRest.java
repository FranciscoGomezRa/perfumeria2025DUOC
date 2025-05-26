package com.gsf.msvc_pedido.clients;

import com.gsf.msvc_pedido.model.Producto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name="msvc-productos",url="localhost:8080/api/v1/productos")
public interface ProductClientRest {

    @GetMapping
    ResponseEntity<List<Producto>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<Producto> findById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Producto> save(@Valid @RequestBody Producto producto);

    @GetMapping("/name/{nombre_producto}")
    Optional<Producto> findByNombreProducto(String nombre_producto);

}