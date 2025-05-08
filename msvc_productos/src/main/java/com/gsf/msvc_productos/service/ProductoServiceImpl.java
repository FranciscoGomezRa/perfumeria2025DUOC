package com.gsf.msvc_productos.service;

import com.gsf.msvc_productos.models.Producto;
import com.gsf.msvc_productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {return this.productoRepository.findAll();}

    @Override
    public Producto findById(Long id) {
        return null;
    }

    @Override
    public Producto save(Producto producto) {
        return null;
    }

}
