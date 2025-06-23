package com.gsf.msvc_sucursal.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "DTO Update Cliente")

public class SucursalUpdateDTO {
    @Schema(description = "Nombre de sucursal", example="Sucursal Vina del Mar")
    private String nombreSucursal;

    @Schema(description = "Direccion de la sucursal", example="Libertad 833")
    private String direccionSucursal;

}
