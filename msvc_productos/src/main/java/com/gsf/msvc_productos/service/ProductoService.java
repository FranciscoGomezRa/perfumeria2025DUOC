package com.gsf.msvc_productos.service;

import com.gsf.msvc_productos.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> findAll();
    Producto findById(Long id);
    Producto save(Producto producto);
    void deleteById(Long id);
    Optional<Producto> findByNombreProducto(String nombre_producto);
}
