package com.gsf.msvc_productos.service;

import com.gsf.msvc_productos.dtos.ProductoUpdateDTO;
import com.gsf.msvc_productos.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> findAll();
    Producto findById(Long id);
    Producto save(Producto producto);
    void deleteById(Long id);
    Producto update(Long idProducto, ProductoUpdateDTO productoUpdateDTO);
    Optional<Producto> findByNombreProducto(String nombreProducto);
}
