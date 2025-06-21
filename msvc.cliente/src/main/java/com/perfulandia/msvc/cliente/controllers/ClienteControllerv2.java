package com.perfulandia.msvc.cliente.controllers;


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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/clientes")
@Validated

@Tag(name= "Clientes Hateoas", description="Operacion Crud de Clientes Hateoas")
public class ClienteControllerv2 {

    @Autowired
    private ClienteService clienteService;




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
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.clienteService.findById(id));
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
