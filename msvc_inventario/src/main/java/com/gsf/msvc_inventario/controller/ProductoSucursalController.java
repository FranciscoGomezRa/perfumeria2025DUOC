package com.gsf.msvc_inventario.controller;

import com.gsf.msvc_inventario.model.entity.ProductoInventario;
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
    public ResponseEntity<List<ProductoInventario>> findAll() {
        List<ProductoInventario> productoInventarios = inventoryService.findAll();
        return ResponseEntity.ok().body(productoInventarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoInventario> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductoInventario> save(@RequestBody @Valid ProductoInventario productoInventario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.save(productoInventario));
    }

    @PatchMapping("/{nombre}/cantidad")
    public ResponseEntity<Void> addCantidadProducto(
            @PathVariable String nombre,
            @RequestParam int cantidad) {

        inventoryService.addCantidadProducto(nombre, cantidad);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content
    }

}
