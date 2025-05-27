package com.gsf.msvc_inventario.model;

import lombok.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sucursal {

    private Long idSucursal;
    private String nombreSucursal;
    private String direccionSucursal;
}
