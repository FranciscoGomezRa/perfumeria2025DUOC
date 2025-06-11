package com.gsf.msvc_productos.dtos;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.Array;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@ToString
@Schema(description = "DTO Update Producto")
public class ProductoUpdateDTO {

    @Schema(description = "Descripcion del Producto", example="Delicioso chocolate")
    private String descripcionProducto;
    @Schema(description = "Nombre del Producto", example="Chocolate Suizo")
    private String nombreProducto;
    @Schema(description = "Precio del Producto", example="4000.0")
    private float precioProducto;

//    @ArraySchema(
//            schema = @Schema(
//                    implementation = pacienteDTO.class
//            )
//    )
//    @Schema(description = "Este es el paciente con el que se trabaja",
//            implementation = PacienteDTO.class
//    )
//    private PacienteDTO paciente;
}
