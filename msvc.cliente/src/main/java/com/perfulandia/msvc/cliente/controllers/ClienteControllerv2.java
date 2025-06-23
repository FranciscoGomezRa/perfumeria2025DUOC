package com.perfulandia.msvc.cliente.controllers;


import com.gsf.msvc_productos.controller.ProductoControllerV2;
import com.gsf.msvc_productos.models.Producto;
import com.perfulandia.msvc.cliente.assemblers.ClienteModelAssembler;
import com.perfulandia.msvc.cliente.dtos.ClienteUpdateDTO;
import com.perfulandia.msvc.cliente.dtos.ErrorDTO;
import com.perfulandia.msvc.cliente.models.Cliente;
import com.perfulandia.msvc.cliente.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v2/clientes")
@Validated

@Tag(name= "Clientes Hateoas", description="Operacion Crud de Clientes Hateoas")
public class ClienteControllerv2 {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler clienteModelAssembler;



    @GetMapping
    @Operation(
            summary="Obtiene todo los Clientes",
            description = "Devuelve un List de Clientes en el Body"
    )

    @ApiResponses(value= {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion Exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Cliente.class)
                    )
            )
    })
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> findAll(){
        List<EntityModel<Cliente>> entityModels = this.clienteService.findAll()
                .stream()
                .map(clienteModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Cliente>> collectionModel = CollectionModel.of (
                entityModels,
                linkTo(methodOn(ClienteControllerv2.class).findAll()).withSelfRel()
        );
        return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(
            summary="Obtiene un Cliente",
            description = "A través de un ID suministrado en el endpoint, devuelve un Cliente con dicho id "
    )

    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(
                    responseCode= "404",
                    description = "Cliente no encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters(value={
            @Parameter(name="id", description = "Este id es id único de cliente", required = true)
    })
    public ResponseEntity<EntityModel<Cliente>> findById(@PathVariable Long id){
        EntityModel<Cliente> entityModel = clienteModelAssembler.toModel(
                clienteService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(entityModel);
    }

    @PostMapping
    @Operation(summary = "Guarda un cliente", description = "Con este método podemos enviar un body(JSON) con los atributos del cliente a ingresar")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201", description = "Guardado exitosamente"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Cliente ya registrado en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            //schema = @Schema(implementation = ErrorDTO.class)))
                            //EXAMPLE OBJECT REEMPLAZA AL DTO
                            examples = @ExampleObject(
                            name= "Error por conflicto de nombres",
                            value="{\"codigo\": \"statusCode\", \"date\": \"fecha\"}")
                    )
            )
    })

    @RequestBody(
            description = "Cliente a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Cliente.class)
            )
    )

    public ResponseEntity<Cliente> save(@Valid @RequestBody Cliente cliente){
                return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.save(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
                clienteService.deleteById(id);

                return ResponseEntity.status(HttpStatus.OK).body("Cliente eliminado");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody ClienteUpdateDTO clienteUpdateDTO){
                return ResponseEntity.status(HttpStatus.OK).body(this.clienteService.update(id, clienteUpdateDTO));
    }
}
