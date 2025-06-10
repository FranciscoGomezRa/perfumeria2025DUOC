package com.gsf.msvc_productos.controller;

import com.gsf.msvc_productos.dtos.ProductoEliminadoDTO;
import com.gsf.msvc_productos.dtos.ProductoUpdateDTO;
import com.gsf.msvc_productos.models.Producto;
import com.gsf.msvc_productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name="Productos", description="Operacion CRUD Productos")
public class ProductoController {

    @Autowired
    private ProductoService productService;


    @GetMapping
    @Operation(
            summary="Obtiene todo los Productos",
            description = "Devuelve un List de Productos en el Body"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa")
    })
    public ResponseEntity<List<Producto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary="Obtiene un Producto",
            description = "A travez de un ID suministrado en el endpoint, devuelve un Producto con ese id"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
            @ApiResponse(responseCode= "404", description = "Producto no Encontrado")
    })
    @Parameters( value={
            @Parameter(name="id",description = "Este es el id Unico del Producto", required = true)
    })
    public ResponseEntity<Producto> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Producto> save(@Valid @RequestBody Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.save(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        productService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Producto Eliminado");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @Valid @RequestBody ProductoUpdateDTO productoUpdateDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.update(id, productoUpdateDTO));
    }
}
