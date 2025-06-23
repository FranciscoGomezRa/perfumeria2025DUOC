package com.gsf.msvc_detallepedido.service;



import com.gsf.msvc_detallepedido.dtos.DetallePedidoUpdateDTO;
import com.gsf.msvc_detallepedido.dtos.idPedidoDTO;
import com.gsf.msvc_detallepedido.model.entity.DetallePedido;

import java.util.List;

public interface DetallePedidoService {

    DetallePedido findById(Long id);
    List<DetallePedido> findAll();
    DetallePedido save(DetallePedido detallePedido);
    void deleteById(Long id);
    DetallePedido update(Long idDetallePedido, DetallePedidoUpdateDTO detallePedidoUpdateDTO);
    List<DetallePedido> BuscadorPorIdPedido(idPedidoDTO idpedidodto);

}
