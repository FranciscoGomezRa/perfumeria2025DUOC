package com.gsf.msvc_detallepedido.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Inventario {


    private Long idInventario;

    private Integer cantidadInventario;

    private Long idProducto;

    private Long idSucursal;



}
