package com.gsf.msvc_detallepedido.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long idCliente;
    private Long idSucursal;
}
