package com.gsf.msvc_sucursal.controllers;


import com.gsf.msvc_sucursal.assamblers.SucursalModelAssembler;
import com.gsf.msvc_sucursal.dtos.ErrorDTO;
import com.gsf.msvc_sucursal.dtos.SucursalUpdateDTO;
import com.gsf.msvc_sucursal.model.Sucursal;
import com.gsf.msvc_sucursal.service.SucursalService;
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
@RequestMapping("api/v2/sucursal")
@Validated

@Tag(name= "Sucursales Hateoas", description="Operacion Crud de Sucursales Hateoas")
public class SucursalControllerv2 {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private SucursalModelAssembler sucursalModelAssembler;



    @GetMapping
    @Operation(
            summary="Obtiene todo los Sucursals",
            description = "Devuelve un List de Sucursals en el Body"
    )

    @ApiResponses(value= {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion Exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Sucursal.class)
                    )
            )
    })
    public ResponseEntity<CollectionModel<EntityModel<Sucursal>>> findAll(){
        List<EntityModel<Sucursal>> entityModels = this.sucursalService.findAll()
                .stream()
                .map(sucursalModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Sucursal>> collectionModel = CollectionModel.of (
                entityModels,
                linkTo(methodOn(SucursalControllerv2.class).findAll()).withSelfRel()
        );
        return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(
            summary="Obtiene un Sucursal",
            description = "A través de un ID suministrado en el endpoint, devuelve un Sucursal con dicho id "
    )

    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(
                    responseCode= "404",
                    description = "Sucursal no encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters(value={
            @Parameter(name="id", description = "Este id es id único de sucursal", required = true)
    })
    public ResponseEntity<EntityModel<Sucursal>> findById(@PathVariable Long id){
        EntityModel<Sucursal> entityModel = sucursalModelAssembler.toModel(
                sucursalService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(entityModel);
    }

    @PostMapping
    @Operation(summary = "Guarda un sucursal", description = "Con este método podemos enviar un body(JSON) con los atributos del sucursal a ingresar")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201", description = "Guardado exitosamente"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Sucursal ya registrado en la base de datos",
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
            description = "Sucursal a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Sucursal.class)
            )
    )

    public ResponseEntity<Sucursal> save(@Valid @RequestBody Sucursal sucursal){
                return ResponseEntity.status(HttpStatus.CREATED).body(this.sucursalService.save(sucursal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
                sucursalService.deleteById(id);

                return ResponseEntity.status(HttpStatus.OK).body("Sucursal eliminado");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Sucursal> update(@PathVariable Long id, @Valid @RequestBody SucursalUpdateDTO sucursalUpdateDTO){
                return ResponseEntity.status(HttpStatus.OK).body(this.sucursalService.update(id, sucursalUpdateDTO));
    }
}
