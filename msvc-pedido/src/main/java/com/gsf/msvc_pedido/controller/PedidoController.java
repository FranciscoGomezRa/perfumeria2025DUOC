package com.gsf.msvc_pedido.controller;


import com.gsf.msvc_pedido.dtos.*;
import com.gsf.msvc_pedido.model.entity.Pedido;
import com.gsf.msvc_pedido.service.PedidoService;
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
@RequestMapping("api/v1/pedido")
@Validated
@Tag(name = "PedidoController",description = "OPERACIONES CRUD")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;


    @GetMapping("/{id}")
    @Operation(
            summary="Obtiene un Pedido",
            description = "A travez de un ID suministrado en el endpoint, devuelve un Pedido con ese id"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa",
                    content = @Content(
                            mediaType = "application/json",
                            schema= @Schema(implementation = Pedido.class)
                    )),
            @ApiResponse(
                    responseCode= "404",
                    description = "Pedido no Encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters( value={
            @Parameter(name="id",description = "Este es el id Unico del Pedido", required = true)
    })
    public ResponseEntity<Pedido> findById(@PathVariable long id){
            return ResponseEntity.status(HttpStatus.OK).body(this.pedidoService.findById(id));};


    @PostMapping("/boleta")
    @Operation(
            summary="Obtiene un Pedido desde un ID(Requiere DTO)",
            description = "Calcula el total de Detalle Pedidos asociados a un Id Pedido"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa",
                    content = @Content(
                            mediaType = "application/json",
                            schema= @Schema(implementation = Pedido.class)
                    )),
            @ApiResponse(
                    responseCode= "404",
                    description = "Pedido no Encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Producto a Crear",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Pedido.class)
            )
    )
    public ResponseEntity<PedidoCompletoDTO> emisionTotalPedidos(@RequestBody@Valid idPedidoDTO idpedidodto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.pedidoService.emisionTotalPedidos(idpedidodto));
    }

    @GetMapping("/boleta/{id}")
    @Operation(
            summary="Obtiene una boleta desde un ID Pedido",
            description = "Calcula el total de Detalle Pedidos asociados a un Id Pedido"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa",
                    content = @Content(
                            mediaType = "application/json",
                            schema= @Schema(implementation = Pedido.class)
                    )),
            @ApiResponse(
                    responseCode= "404",
                    description = "Pedido no Encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<PedidoCompletoDTO> emisionPedidoCalculado(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.pedidoService.emisionPedidoCalculado(id));
    }


    @GetMapping
    @Operation(
            summary="Obtiene todo los Pedidos",
            description = "Devuelve un List de Pedidos en el Body"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa",
                    content = @Content(
                            mediaType = "application/json",
                            schema= @Schema(implementation = Pedido.class)
                    ))
    })
    public ResponseEntity<List<Pedido>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.pedidoService.findAll());
    }

    @PostMapping
    @Operation(summary= "Guarda un Pedido",description = "Con este metodo podemos enviar un body(JSON) con los atributos del producto a ingresar")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201",description = "Guardo exitoso",content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Pedido.class)
            )),
            @ApiResponse(
                    responseCode = "409",
                    description = "El Pedido guardado ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType= "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )

            )
    })
    //ESTE REQUEST BODY ES DE LA DOCUMENTACION, Relativo a la entrada que estoy esperando
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Pedido a Crear",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Pedido.class)
            )
    )
    public ResponseEntity<Pedido> save(@Valid @RequestBody PedidoDTO pedidodto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService.save(pedidodto));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary="Borra un Pedido",
            description = "A travez de un ID suministrado en el endpoint, borra un Pedido con ese id"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa",content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Pedido.class)
            )),
            @ApiResponse(
                    responseCode= "404",
                    description = "Pedido no Encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        pedidoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Pedido Eliminado");
    }

    @PostMapping("/cliente")
    @Operation(summary= "Busca los Pedidos asociados a un Cliente",description = "Requiere un DTO con un ID Cliente")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Success!",content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Pedido.class)
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "El Cliente no existe",
                    content = @Content(
                            mediaType= "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )

            )
    })
    //ESTE REQUEST BODY ES DE LA DOCUMENTACION, Relativo a la entrada que estoy esperando
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "busca un cliente",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Pedido.class)
            )
    )
    public List<Pedido> findByClienteId(idClienteDTO idclientedto) {
        return this.pedidoService.findByClienteId(idclientedto);
        }

    @PatchMapping("/{id}")
    @Operation(
            summary="Actualiza un Pedido",
            description = "A travez de un ID suministrado en el endpoint, y una cantidad en el body, Actualiza un Pedido con ese id"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
            @ApiResponse(
                    responseCode= "404",
                    description = "Pedido no Encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Pedido a Actualizar",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Pedido.class)
            )
    )
    public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody PedidoDTO pedidodto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.pedidoService.update(id, pedidodto));
    }

}




