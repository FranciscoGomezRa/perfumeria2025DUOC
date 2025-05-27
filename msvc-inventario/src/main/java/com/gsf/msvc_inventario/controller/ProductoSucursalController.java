package com.gsf.msvc_inventario.controller;

import com.gsf.msvc_inventario.dtos.BuscaStockPorIdDTO;
import com.gsf.msvc_inventario.dtos.BuscadorPorIDSucursalDTO;
import com.gsf.msvc_inventario.dtos.ProductoInventarioInfoDTO;
import com.gsf.msvc_inventario.model.entity.Inventario;
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
    public ResponseEntity<List<Inventario>> findAll() {
        List<Inventario> inventarios = inventoryService.findAll();
        return ResponseEntity.ok().body(inventarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Inventario> save(@RequestBody @Valid Inventario inventario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.save(inventario));
    }

    @PostMapping("/productos")
    public ResponseEntity<List<Inventario>> save(@RequestBody @Valid BuscadorPorIDSucursalDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.findByIdSucursal(dto.getIdSucursal()));
    }

    @PostMapping("/stock")
    public ResponseEntity<Boolean> stockInventario(@RequestBody@Valid BuscaStockPorIdDTO stockSolicitado){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.stockInventario(stockSolicitado));
    }


}
