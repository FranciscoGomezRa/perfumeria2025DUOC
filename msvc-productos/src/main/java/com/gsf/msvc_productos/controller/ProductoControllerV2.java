package com.gsf.msvc_productos.controller;

import com.gsf.msvc_productos.assemblers.ProductoModelAssembler;
import com.gsf.msvc_productos.dtos.ErrorDTO;
import com.gsf.msvc_productos.dtos.ProductoUpdateDTO;
import com.gsf.msvc_productos.models.Producto;
import com.gsf.msvc_productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("api/v2/productos")
@Validated

@Tag(name="Productos Hateoas", description="Operacion CRUD Productos hateoas")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productService;

    @Autowired
    ProductoModelAssembler productoModelAssembler;


    @GetMapping
    @Operation(
            summary="Obtiene todo los Productos",
            description = "Devuelve un List de Productos en el Body"
    )

    @ApiResponses(value= {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion Exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Producto.class)
                    )
            )
    })
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> findAll(){
        List<EntityModel<Producto>> entityModels = this.productService.findAll()
                .stream()
                .map(productoModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Producto>> collectionModel = CollectionModel.of (
                entityModels,
                linkTo(methodOn(ProductoControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(
            summary="Obtiene un Producto",
            description = "A travez de un ID suministrado en el endpoint, devuelve un Producto con ese id"
    )

    @ApiResponses(value= {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion Exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Producto.class)
                    )
            ),
            @ApiResponse(
                    responseCode= "404",
                    description = "Producto no Encontrado con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters( value={
            @Parameter(name="id",description = "Este es el id Unico del Producto", required = true)
    })
    public ResponseEntity<Producto> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.findById(id));
    }

    @PostMapping
    @Operation(summary= "Guarda un Producto",description = "Con este metodo podemos enviar un body(JSON) con los atributos del producto a ingresar")
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "201",
                    description = "Guardo exitoso",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Producto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El producto guardado ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType= "application/json",
                            //schema = @Schema(implementation = ErrorDTO.class)))
                            //EXAMPLE OBJECT REMPLAZA AL DTO
                            examples = @ExampleObject(
                                    name="Error por conflicto de nombre",
                                    value="{\"codigo\": \"statusCode\", \"date\": \"fecha\"}")
                    )

            )
    })
    //ESTE REQUEST BODY ES DE LA DOCUMENTACION, Relativo a la entrada que estoy esperando
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Producto a Crear",
            content = @Content(
                    mediaType = "application/json",
                    schema= @Schema(implementation = Producto.class)
    )
    )
    public ResponseEntity<Producto> save(@Valid @RequestBody Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.save(producto));
    }
    //MANTENER APRETADO ALT GR PARA CHECKEAR CODIGOS HTML

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
