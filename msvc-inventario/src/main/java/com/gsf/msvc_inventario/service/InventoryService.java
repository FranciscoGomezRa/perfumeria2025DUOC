package com.gsf.msvc_inventario.service;


import com.gsf.msvc_inventario.dtos.ProductoInventarioInfoDTO;
import com.gsf.msvc_inventario.model.entity.Inventario;

import java.util.List;

public interface InventoryService {
    List<Inventario> findAll();
    ProductoInventarioInfoDTO findById(Long id);
    Inventario save(Inventario inventario);
}
