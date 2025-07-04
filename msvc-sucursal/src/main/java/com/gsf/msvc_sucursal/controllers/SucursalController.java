package com.gsf.msvc_sucursal.controllers;

import com.gsf.msvc_sucursal.dtos.SucursalUpdateDTO;
import com.gsf.msvc_sucursal.service.SucursalService;
import com.gsf.msvc_sucursal.model.Sucursal;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursal")
@Validated

public class SucursalController {

        @Autowired
        private SucursalService sucursalService;

        @GetMapping
        public ResponseEntity<List<Sucursal>> findAll(){
            return ResponseEntity.status(HttpStatus.OK).body(this.sucursalService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Sucursal>findById(@PathVariable Long id){
            return ResponseEntity.status(HttpStatus.OK).body(sucursalService.findById(id));
        }

        @PostMapping
        public ResponseEntity<Sucursal> save(@Valid @RequestBody Sucursal sucursal) {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.sucursalService.save(sucursal));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            this.sucursalService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        @PatchMapping("/{id}")
        public ResponseEntity<Sucursal> update(@PathVariable Long id, @Valid @RequestBody SucursalUpdateDTO sucursalUpdateDTO) {
            return ResponseEntity.status(HttpStatus.OK).body(this.sucursalService.update(id, sucursalUpdateDTO));
        }
}
