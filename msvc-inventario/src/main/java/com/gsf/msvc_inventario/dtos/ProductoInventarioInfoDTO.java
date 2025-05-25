package com.gsf.msvc_inventario.dtos;

import com.gsf.msvc_inventario.model.entity.ProductoInventario;
import lombok.*;

@Getter@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductoInventarioInfoDTO {

    private ProductoInventario productoInventario;
    private ProductoDTO productoDTO;


}
