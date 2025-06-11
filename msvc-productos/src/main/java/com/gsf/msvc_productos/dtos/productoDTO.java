package com.gsf.msvc_productos.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@ToString
public class productoDTO {

    //FALTA IMPLEMENTAR REQUESTBODY DE ESTE DTO PARA CREAR PRODUCTOS

    private String descripcionProducto;
    private String nombreProducto;
    private Float precioProducto;
}
