package com.gsf.msvc_pedido.dtos;


import com.gsf.msvc_pedido.model.DetallePedido;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PedidoCompletoDTO {



    private Long idPedido;
    private String nombreCliente;
    private List<DetallePedido> listaDetallePedidos;
    private Double totalPedido;

}
