package com.gsf.msvc_inventario.controller;

import com.gsf.msvc_inventario.model.entity.ProductoSucursal;
import com.gsf.msvc_inventario.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventario")
@Validated

public class ProductoSucursalController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<ProductoSucursal>> findAll() {
        List<ProductoSucursal> productoSucursals = inventoryService.findAll();
        return ResponseEntity.ok().body(productoSucursals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoSucursal> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductoSucursal> save(@RequestBody @Valid ProductoSucursal productoSucursal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.save(productoSucursal));
    }

    @PatchMapping("/{nombre}/cantidad")
    public ResponseEntity<Void> addCantidadProducto(
            @PathVariable String nombre,
            @RequestParam int cantidad) {

        inventoryService.addCantidadProducto(nombre, cantidad);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content
    }

}
