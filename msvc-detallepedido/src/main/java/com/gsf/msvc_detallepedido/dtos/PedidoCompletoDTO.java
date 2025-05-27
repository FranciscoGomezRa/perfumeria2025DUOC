package com.gsf.msvc_detallepedido.dtos;



import com.gsf.msvc_detallepedido.model.entity.DetallePedido;
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

}
