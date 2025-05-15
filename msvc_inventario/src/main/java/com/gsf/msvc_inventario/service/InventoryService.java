package com.gsf.msvc_inventario.service;


import com.gsf.msvc_inventario.model.entity.ProductoSucursal;

import java.util.List;

public interface InventoryService {
    List<ProductoSucursal> findAll();
    ProductoSucursal findById(Long id);
    ProductoSucursal save(ProductoSucursal productoSucursal);
    public ProductoSucursal addCantidadProducto(String nombre, Integer cantidad);
}
