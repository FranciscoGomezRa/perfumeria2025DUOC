package com.gsf.msvc_pedido.dtos;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class PedidoUpdateDTO {
    private Long idSucursal;
    private Long idCliente;
    private Double totalPedido;
}
