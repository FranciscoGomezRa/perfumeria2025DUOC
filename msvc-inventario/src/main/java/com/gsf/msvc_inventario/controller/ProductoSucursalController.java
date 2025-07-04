package com.gsf.msvc_inventario.controller;

import com.gsf.msvc_inventario.dtos.*;
import com.gsf.msvc_inventario.model.entity.Inventario;
import com.gsf.msvc_inventario.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("api/v1/inventario")
@Validated

@Tag(name="Inventarios", description="Operacion CRUD Inventario")
public class ProductoSucursalController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    @Operation(
            summary="Obtiene todo los Inventarios",
            description = "Devuelve un List de Inventarios en el Body"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa")
    })
    public ResponseEntity<List<Inventario>> findAll() {
        List<Inventario> inventarios = inventoryService.findAll();
        return ResponseEntity.ok().body(inventarios);
    }

    @GetMapping("/{id}")
    @Operation(
            summary="Obtiene un Inventario",
            description = "A travez de un ID suministrado en el endpoint, devuelve un Inventario con ese id"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
            @ApiResponse(
                    responseCode= "404",
                    description = "Inventario no Encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters( value={
            @Parameter(name="id",description = "Este es el id Unico del Inventario", required = true)
    })
    public ResponseEntity<Inventario> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.findById(id));
    }

    @PostMapping
    @Operation(summary= "Guarda un Inventario",description = "Con este metodo podemos enviar un body(JSON) con los atributos del producto a ingresar")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201",description = "Guardo exitoso"),
            @ApiResponse(
                    responseCode = "409",
                    description = "El Inventario guardado ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType= "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )

            )
    })
    //ESTE REQUEST BODY ES DE LA DOCUMENTACION, Relativo a la entrada que estoy esperando
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Inventario a Crear",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Inventario.class)
            )
    )
    public ResponseEntity<Inventario> save(@RequestBody @Valid Inventario inventario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.save(inventario));
    }


    @PostMapping("/productos")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Busca todos los inventarios asociados a un ID de SUCURSAL, REQUIERE UN DTO",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Inventario.class)
            )
    )
    public ResponseEntity<List<Inventario>> save(@RequestBody @Valid BuscadorPorIDSucursalDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.findByIdSucursal(dto.getIdSucursal()));
    }


    @PostMapping("/stock")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "busca Stock asociado a un inventario utilizando un DTO",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Inventario.class)
            )
    )
    public ResponseEntity<Boolean> stockInventario(@RequestBody@Valid BuscaStockPorIdDTO stockSolicitado){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.stockInventario(stockSolicitado));
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary="Borra un Inventario",
            description = "A travez de un ID suministrado en el endpoint, borra un Inventario con ese id"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
            @ApiResponse(
                    responseCode= "404",
                    description = "Inventario no Encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<String> delete(Long id) {
        inventoryService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Inventario Eliminado");
    }

    @PatchMapping("/{id}")
    @Operation(
            summary="Actualiza un Inventario",
            description = "A travez de un ID suministrado en el endpoint, y una cantidad en el body, Actualiza un Inventario con ese id"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
            @ApiResponse(
                    responseCode= "404",
                    description = "Inventario no Encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Inventario a Actualizar",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Inventario.class)
            )
    )
    public ResponseEntity<Inventario> update(@PathVariable Long id, @Valid @RequestBody InventoryUpdateDTO inventoryUpdateDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.inventoryService.update(id, inventoryUpdateDTO));
    }



}
