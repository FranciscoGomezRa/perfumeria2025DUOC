package com.gsf.msvc_inventario.service;


import com.gsf.msvc_inventario.model.entity.ProductoInventario;

import java.util.List;

public interface InventoryService {
    List<ProductoInventario> findAll();
    ProductoInventario findById(Long id);
    ProductoInventario save(ProductoInventario productoInventario);
    ProductoInventario addCantidadProducto(String nombre, Integer cantidad);
}
