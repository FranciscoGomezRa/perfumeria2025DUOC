package com.gsf.msvc_pedido.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto {


    private Long idProducto;
    private String descripcionProducto;
    private String nombreProducto;
    private Float precioProducto;

}
