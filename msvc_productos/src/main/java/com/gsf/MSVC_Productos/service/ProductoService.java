package com.gsf.MSVC_Productos.service;

import com.gsf.MSVC_Productos.models.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> findAll();
    Producto findById(Long id);
    Producto save(Producto producto);
}
