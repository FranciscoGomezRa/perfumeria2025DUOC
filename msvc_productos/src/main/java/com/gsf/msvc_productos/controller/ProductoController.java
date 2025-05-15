package com.gsf.msvc_productos.controller;

import com.gsf.msvc_productos.models.Producto;
import com.gsf.msvc_productos.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productos")
@Validated
public class ProductoController {

    @Autowired
    private ProductoService productService;

    @GetMapping
    public ResponseEntity<List<Producto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.findAll());
    }

    @GetMapping("/name/{nombre_producto}")
    public ResponseEntity<Producto> findByNombreProducto(@PathVariable String nombre_producto){
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.findByNombreProducto(nombre_producto).orElse(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Producto> save(@Valid @RequestBody Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.save(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Producto>> deleteById(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
