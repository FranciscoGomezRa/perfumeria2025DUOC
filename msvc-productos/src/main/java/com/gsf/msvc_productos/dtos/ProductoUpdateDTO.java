package com.gsf.msvc_productos.dtos;

import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@ToString
public class ProductoUpdateDTO {

    private String descripcionProducto;
    private String nombreProducto;
    private float precioProducto;
}
