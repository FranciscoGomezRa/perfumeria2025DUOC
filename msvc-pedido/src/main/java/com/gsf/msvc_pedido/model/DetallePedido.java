package com.gsf.msvc_pedido.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetallePedido {


    private Long idDetallePedido;
    private Long idPedido;
    private Long idProducto;
    private Integer cantidad;
    private Double total;




}
