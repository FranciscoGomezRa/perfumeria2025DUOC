package com.example.MSVC_Creacion_Descuento.controllers;


import com.example.MSVC_Creacion_Descuento.models.Descuento;
import com.example.MSVC_Creacion_Descuento.services.DescuentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/descuentos")
@Validated

public class DescuentoController {
    @Autowired
    private DescuentoService descuentoService;

    @GetMapping
    public ResponseEntity<List<Descuento>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.descuentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Descuento>findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(descuentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Descuento> save(@Valid @RequestBody Descuento descuento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.descuentoService.save(descuento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.descuentoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


