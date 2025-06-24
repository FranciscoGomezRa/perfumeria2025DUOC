package com.gsf.msvc_detallepedido.controller;



import com.gsf.msvc_detallepedido.dtos.DetallePedidoUpdateDTO;
import com.gsf.msvc_detallepedido.dtos.ErrorDTO;
import com.gsf.msvc_detallepedido.dtos.idPedidoDTO;
import com.gsf.msvc_detallepedido.model.entity.DetallePedido;
import com.gsf.msvc_detallepedido.service.DetallePedidoService;
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
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/detallepedido")
@Validated
@Tag(name="DetallePedido", description = "OPERACIONES CRUD")
public class DetallePedidoController {


    @Autowired
    private DetallePedidoService detallePedidoService;


    @GetMapping("/{id}")
    @Operation(
            summary="Obtiene un Detalle Pedido",
            description = "A travez de un ID suministrado en el endpoint, devuelve un Detalle Pedido con ese id"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = DetallePedido.class)
                    )),
            @ApiResponse(
                    responseCode= "404",
                    description = "Detalle Pedido no Encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters( value={
            @Parameter(name="id",description = "Este es el id Unico del Detalle Pedido", required = true)
    })
    public ResponseEntity<DetallePedido> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.detallePedidoService.findById(id));
    }

    @GetMapping
    @Operation(
            summary="Obtiene todo los DetallePedido",
            description = "Devuelve un List de Detalle Pedido en el Body"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = DetallePedido.class)
                    ))
    })
    public ResponseEntity<List<DetallePedido>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.detallePedidoService.findAll());
    }

    @PostMapping
    @Operation(summary= "Guarda un Detalle Pedido",description = "Con este metodo podemos enviar un body(JSON)")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201",description = "Guardo exitoso"),
            @ApiResponse(
                    responseCode = "409",
                    description = "El Detalle Pedido guardado ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType= "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )

            )
    })
    //ESTE REQUEST BODY ES DE LA DOCUMENTACION, Relativo a la entrada que estoy esperando
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Detalle Detalle Pedido a Crear",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = DetallePedido.class)
            )
    )
    public ResponseEntity<DetallePedido> save(@Valid @RequestBody DetallePedido detallePedido) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.detallePedidoService.save(detallePedido));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary="Borra un Detalle Pedido",
            description = "A travez de un ID suministrado en el endpoint, borra un Detalle Pedido con ese id"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
            @ApiResponse(
                    responseCode= "404",
                    description = "Detalle Pedido no Encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        detallePedidoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Detalle Pedido Eliminado");
    }


    @PostMapping("/pedido")
    @Operation(
            summary="Obtiene una Lista de Detalle Pedidos",
            description = "Obtiene todos los Detalle Pedido Asociados a ese ID"
    )

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = DetallePedido.class)
                    )),
            @ApiResponse(
                    responseCode= "404",
                    description = "Detalle Pedido no Encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters( value={
            @Parameter(name="id",description = "Este es el id Unico del Detalle Pedido", required = true)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Recibe un ID PEDIDO",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = DetallePedido.class)
            )
    )
    public ResponseEntity<List<DetallePedido>> BuscadorPorIdPedido(@RequestBody@Valid idPedidoDTO idpedidodto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.detallePedidoService.BuscadorPorIdPedido(idpedidodto));
    }

    @PatchMapping("/id")
    @Operation(
            summary="Actualiza un Detalle pedido",
            description = "Necesita un body que contenga ID y un BODY con los datos a modificar"
    )
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
            @ApiResponse(
                    responseCode= "404",
                    description = "Detalle Pedido no Encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Detalle Pedido a Actualizar",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = DetallePedido.class)
            )
    )
    public ResponseEntity<DetallePedido> update(@PathVariable Long id,@Valid @RequestBody DetallePedidoUpdateDTO DetallePedidoUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.detallePedidoService.update(id, DetallePedidoUpdateDTO));

    }


}
