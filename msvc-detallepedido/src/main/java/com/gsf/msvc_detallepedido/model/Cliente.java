package com.gsf.msvc_detallepedido.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cliente {


    private Long idCliente;
    private String rut;
    private String nombreCliente;
    private String emailCliente;
}
