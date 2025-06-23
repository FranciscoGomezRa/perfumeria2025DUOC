package com.gsf.msvc_inventario.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Schema(hidden = true)
public class InventoryUpdateDTO {
    private Integer cantidadInventario;
}
