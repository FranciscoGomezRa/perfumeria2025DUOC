package com.perfulandia.msvc.cliente.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "DTO Update Cliente")

public class ClienteUpdateDTO {
    @Schema(description = "Rut del cliente", example="17142360-0")
    private String rut;
    @Schema(description = "Nombre del Cliente", example="Gonzalo")
    private String nombreCliente;
    @Schema(description = "Email del cliente", example="gonzalo123@gmail.com")
    private String emailCliente;
}
