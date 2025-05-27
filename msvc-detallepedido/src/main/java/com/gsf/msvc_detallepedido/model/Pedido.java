package com.gsf.msvc_detallepedido.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pedido {

    private Long idPedido;
    private LocalDate fechaPedido;
    private Double totalPedido;
    private Long idCliente;
    private Long idSucursal;

}
