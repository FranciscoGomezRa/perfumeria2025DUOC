package com.gsf.msvc_inventario.dtos;

import com.gsf.msvc_inventario.model.entity.Inventario;
import lombok.*;

@Getter@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductoInventarioInfoDTO {

    private Inventario inventario;
    private ProductoDTO productoDTO;


}
