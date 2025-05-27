package com.gsf.msvc_detallepedido.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BuscaStockPorIdDTO {

    private Long idSucursal;
    private Long idProducto;
    private Integer cantidadSolicitada;
}
