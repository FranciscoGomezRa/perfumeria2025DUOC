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
@RequestMapping("/api/v1/clientes")
@Validated
@Tag(name = "Clientes", description = "Operaciones Crud Clientes")

public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtiene todos los clientes", description = "Devuelve un list de Clientes en el Body")

    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa")})
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.clienteService.findAll());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un cliente", description = "A traves del id suministrado devuelve el cliente con esa id")


    @ApiResponses ( value = {
            @ApiResponse(responseCode = "200", description = "operacion exitosa"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente no encontrado",
                    content = @Content(
                            mediaType =  "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                                      )
                        )
    })
    @Parameters(value = {
            @Parameter(name="id", description = "Este es el id unico del cliente", required = true)
    })

    public ResponseEntity<Cliente>findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
    }

    @PostMapping
    @Operation(summary= "Guarda un cliente", description = "Con este metodo podemos enviar un body en JSON con los atributes del cliente a ingresar")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Guardado exitosamente"),
            @ApiResponse(
                    responseCode = "409",
                    description = "El cliente ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            //schema = @Schema(implementation = ErrorDTO.class)))
                            //EXAMPLE OBJECT REMPLAZA AL DTO
                            examples = @ExampleObject(
                                    name="Error por conflicto de nombre",
                                    value = "{\"codigo\": \"statuscode\", \"date\": \"fecha\"}")
                    )
            )
    })

    public ResponseEntity<Cliente> save(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.save(cliente));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.clienteService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente eliminado");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody ClienteUpdateDTO clienteUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.clienteService.update(id, clienteUpdateDTO));
    }




}
